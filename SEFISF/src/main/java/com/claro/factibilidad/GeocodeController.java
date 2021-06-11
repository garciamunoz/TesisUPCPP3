package com.claro.factibilidad;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;

import com.claro.factibilidad.bean.GeocodeResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URLEncoder;
@RestController
public class GeocodeController {
   @RequestMapping(path = "/geocode", method = RequestMethod.GET )
   public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
       OkHttpClient client = new OkHttpClient();
       String encodedAddress = URLEncoder.encode(address, "UTF-8");
       Request request = new Request.Builder()
               .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
               .get()
               .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
               .addHeader("x-rapidapi-key", "6b224667abmsh2b9fab324adba5ap19749fjsn87bcdcbdacb3"/*  Use your API Key here */)
               .build();
       ResponseBody responseBody = client.newCall(request).execute().body();
       ObjectMapper objectMapper = new ObjectMapper();
       GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
       return result;
   }
}