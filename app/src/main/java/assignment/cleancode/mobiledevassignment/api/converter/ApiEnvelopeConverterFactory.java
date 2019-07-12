package assignment.cleancode.mobiledevassignment.api.converter;



import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import assignment.cleancode.mobiledevassignment.api.ResponseEnvelope;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class ApiEnvelopeConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        Type wrappedType = TypeToken.getParameterized(ResponseEnvelope.class, type).getType();

        Converter<ResponseBody, ?> delegate =
                retrofit.nextResponseBodyConverter(this, wrappedType, annotations);
        return new ApiEnvelopeConverter(delegate);
    }
}
