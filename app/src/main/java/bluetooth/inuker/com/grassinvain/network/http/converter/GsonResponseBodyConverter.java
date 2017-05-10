package bluetooth.inuker.com.grassinvain.network.http.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import bluetooth.inuker.com.grassinvain.network.http.Response;
import bluetooth.inuker.com.grassinvain.network.http.subscriber.ApiException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        T response = null;
        try {
            response = adapter.read(jsonReader);
        } catch (Exception e) {
            throw new ApiException(ApiException.ErrorType.PARSE_JSON, e.getMessage());
        } finally {
            value.close();
        }

        if (((Response) response).isSuccess()) {
            return response;
        } else {
            throw new ApiException(ApiException.ErrorType.MANUAL, ((Response) response).errorMsg);
        }


    }
}
