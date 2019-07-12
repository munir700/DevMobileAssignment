package assignment.cleancode.mobiledevassignment.api.converter;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


public class ApiConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

//        Type wrappedType = TypeToken.getParameterized(ApiResponseEnvelope.class, type).getType();

        Converter<ResponseBody, ?> delegate =
                retrofit.nextResponseBodyConverter(this, type, annotations);
        return new ApiConverter(delegate);
    }
}

