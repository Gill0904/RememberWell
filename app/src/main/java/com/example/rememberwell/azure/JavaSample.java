package com.example.rememberwell.azure;

import com.example.rememberwell.ui.fragment.registroFragment;


import java.net.URI;

public class JavaSample
{
    public static String sentimiento;
    public static void main(String[] args)
    {
        /*HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://textanalyticssw.cognitiveservices.azure.com/text/analytics/v3.1-preview.5/sentiment");
            builder.setParameter("stringIndexType", "TextElement_v8");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "b9fa3d12826d4c2da16f94d1b2f826e5");


            // Request body
            StringEntity reqEntity = new StringEntity("" +
                    "{\n" +
                    "\t\"documents\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"id\": \"1\",\n" +
                    "\t\t\t\"language\": \"es\",\n" +
                    "\t\t\t\"text\": "+ registroFragment.texto+"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }*/
    }
}
