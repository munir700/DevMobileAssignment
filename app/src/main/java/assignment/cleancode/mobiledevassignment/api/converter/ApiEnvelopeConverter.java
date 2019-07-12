package assignment.cleancode.mobiledevassignment.api.converter;


import com.bumptech.glide.load.HttpException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import assignment.cleancode.mobiledevassignment.api.ResponseEnvelope;
import assignment.cleancode.mobiledevassignment.models.ArrayListWithTotalResultCount;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ApiEnvelopeConverter<T> implements Converter<ResponseBody, T> {

    Converter<ResponseBody, ResponseEnvelope<T>> delegate;
    Converter<ResponseBody, ResponseEnvelope> upperWrapDelegate;

    ResponseBody updateResponseBody;

    ApiEnvelopeConverter(Converter<ResponseBody, ResponseEnvelope<T>> delegate) {
        this.delegate = delegate;

        this.upperWrapDelegate = new Converter<ResponseBody, ResponseEnvelope>() {
            @Override
            public ResponseEnvelope convert(ResponseBody responseBody) throws IOException {
                String response = responseBody.string();
                updateResponseBody = ResponseBody.create(responseBody.contentType(), response);
                Type typeOfObjectList = new TypeToken<ResponseEnvelope>() {
                }.getType();
                Gson gson = new GsonBuilder()
                        .setExclusionStrategies(ResponseEnvelope.getExclusionStrategy())
                        .create();
                return gson.fromJson(response, typeOfObjectList);
            }
        };
    }


    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        ResponseEnvelope metaResponse = this.upperWrapDelegate.convert(responseBody);
        try {
            if (metaResponse.listItem != null) {
                ResponseEnvelope<T> envelopeList = delegate.convert(updateResponseBody);
                if (envelopeList.listItem instanceof ArrayListWithTotalResultCount) {
                    ((ArrayListWithTotalResultCount) envelopeList.listItem).setTotalNumberOfResults(envelopeList.totalResults);
                    ((ArrayListWithTotalResultCount) envelopeList.listItem).setPage(envelopeList.page);
                    ((ArrayListWithTotalResultCount) envelopeList.listItem).setTotalNumberOfPages(envelopeList.totalPages);
                }
                return envelopeList.listItem;
            } else {
                throw new HttpException(metaResponse.message, metaResponse.status);
            }
        } catch (HttpException httpEx) {
            throw httpEx;
        } catch (Exception e) {
            //TODO : Handle it more gracefully. Define status and error.
            throw new HttpException(null);
        }
    }

}
