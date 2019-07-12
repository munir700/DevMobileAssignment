package assignment.cleancode.mobiledevassignment.api.converter;

import com.bumptech.glide.load.HttpException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class ApiConverter<T> implements Converter<ResponseBody, T> {
    Converter<ResponseBody, T> delegate;
    Converter<ResponseBody, T> upperWrapDelegate;

    ResponseBody updateResponseBody;

    ApiConverter(Converter<ResponseBody, T> delegate) {
        this.delegate = delegate;

        this.upperWrapDelegate = new Converter<ResponseBody, T>() {
            @Override
            public T convert(ResponseBody responseBody) throws IOException {
                String response = responseBody.string();
                updateResponseBody = ResponseBody.create(responseBody.contentType(), response);
                Type typeOfObjectsList = new TypeToken<T>() {
                }.getType();
                Gson gson = new GsonBuilder().create();
                return gson.fromJson(response, typeOfObjectsList);
            }
        };

    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        try {
            T metaDelegate = this.upperWrapDelegate.convert(responseBody);
            if (metaDelegate != null) {
                T envelope = delegate.convert(updateResponseBody);
                return envelope;
            }
            return null;
        } catch (HttpException httpEx) {
            throw httpEx;
        } catch (Exception e) {
            //TODO : Handle it more gracefully. Define status and error.
            throw new HttpException(null);
        }

    }


}