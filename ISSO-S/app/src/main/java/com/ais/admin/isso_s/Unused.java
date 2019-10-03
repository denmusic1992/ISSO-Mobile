package com.ais.admin.isso_s;

public class Unused {
    // Выполнить синхронизацию сведений
    /*private void doSync() {

        if (isNullOrEmpty(user) || isNullOrEmpty(pass) || isNullOrEmpty(host)) return;
        try {

            Map<String, Object> params = new HashMap<>();
            params.put("user", user);
            params.put("pass", pass);
            JSONObject jsonResponse = Connect("GetSessionId", params);
            if (jsonResponse == null) return;

            UUID session = UUID.fromString(jsonResponse.getString("GetSessionIdResult"));
            if (session.equals(new UUID(0l, 0l))) {
                result = "Учетные данные не верны или вы не имеете прав для работы с системой";
                return;
            }

            params = new HashMap<>();
            params.put("id", session);
            jsonResponse = Connect("GetIssoList", params);
            assert jsonResponse != null;
            String issoList = jsonResponse.getString("GetIssoListResult");
            if (issoList == null || issoList.equals("null")) {
                jsonResponse = Connect("GetMessage", params);
                assert jsonResponse != null;
                result = jsonResponse.getString("GetMessageResult");
                return;
            }
            Gson gson = new Gson();
            Isso[] isso = gson.fromJson(issoList, Isso[].class);

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            issoList = "";
            for (Isso i : isso)
                issoList += "," + i.CIsso;
            db.execSQL("delete from I_ISSO where C_ISSO not in (" + issoList.substring(1) + ")");
            for (Isso i : isso)
            {
                ContentValues vals = new ContentValues();
                vals.put("C_ISSO", i.CIsso);
                vals.put("NAME", i.Name);
                vals.put("FULLNAME", i.FullName);
                vals.put("DORNAME", i.DorName);
                vals.put("W_ISSO", i.WIsso);
                vals.put("OBSTACLE", i.Obstacle);
                vals.put("LENGTH", i.Length);
                vals.put("LATITUDE", i.Latitude);
                vals.put("LONGITUDE", i.Longitude);
                db.insertWithOnConflict("I_ISSO", null, vals, SQLiteDatabase.CONFLICT_IGNORE);
            }

            Cursor cr = db.query("RATING", new String[]{"C_ISSO", "RATING", "RATINGDATE", "COMMENTS"}, "SYNC = 0", null, null, null, null);
            cr.moveToFirst();
            IssoRating[] ratings = new IssoRating[cr.getCount()];
            for(int i = 0; i < cr.getCount(); i++)
            {
                ratings[i] = new IssoRating();
                ratings[i].CIsso = cr.getInt(0);
                ratings[i].RatingIsso = cr.getInt(1);
                ratings[i].RatingDate = cr.getLong(2);
                ratings[i].RatingExt = cr.getString(3);
                cr.moveToNext();
            }

            params = new HashMap<>();
            params.put("id", session);

            params.put("rating", ratings);
            jsonResponse = Connect("SetRatingInfo", params);
            assert jsonResponse != null;
            if(jsonResponse.getInt("SetRatingInfoResult") == -1)
            {
                params = new HashMap<>();
                params.put("id", session);
                jsonResponse = Connect("GetMessage", params);
                assert jsonResponse != null;
                result = jsonResponse.getString("GetMessageResult");
                return;
            }

            for (Isso i : isso)
            {
                params = new HashMap<>();
                params.put("id", session);
                params.put("isso", i.CIsso);
                jsonResponse = Connect("GetRatingInfo", params);
                assert jsonResponse != null;
                String response = jsonResponse.getString("GetRatingInfoResult");
                if (response == null || response.equals("null"))
                {
                    params = new HashMap<>();
                    params.put("id", session);
                    jsonResponse = Connect("GetMessage", params);
                    assert jsonResponse != null;
                    result = jsonResponse.getString("GetMessageResult");
                    return;
                }

                IssoRating[] rates = gson.fromJson(response, IssoRating[].class);

                for(IssoRating rate : rates)
                {
                    ContentValues vals = new ContentValues();
                    vals.put("C_ISSO", rate.CIsso);
                    vals.put("RATINGDATE", rate.RatingDate);
                    vals.put("RATING", rate.RatingIsso);
                    vals.put("COMMENTS", rate.RatingExt);
                    vals.put("SYNC", true);
                    db.insertWithOnConflict("RATING", null, vals, SQLiteDatabase.CONFLICT_IGNORE);
                }
            }
            db.rawQuery("update RATING set SYNC = 1", null);

            params = new HashMap<>();
            params.put("id", session);
            Connect("CloseSession", params);
            result = "Синхронизация выполнена успешно";


        } catch (Exception e)
        {
            result = e.toString();// e.getMessage();
            Log.d("Tag", "Гуляй Вася");
        }
    }




    private JSONObject Connect(String address, Map<String, Object> params) throws JSONException, IOException
    {
        URL uri = new URL(String.format("http://%s:%s/ais7UpdateServer.Iais7RatingMobile/" + address, host, port));

        JSONObject jo1 = new JSONObject(params);
        String json = new Gson().toJson(params);
		for(String key : params.keySet())
			jo1.put(key, params.get(key));
        jo1.put("user", login);
        jo1.put("pass", pass);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setRequestProperty("Content-Type","application/json; charset=utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Pigeon");
        conn.setChunkedStreamingMode(0);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.connect();

        OutputStream wr = conn.getOutputStream();
        wr.write(json.getBytes());
        wr.flush();
        wr.close();

        int status = conn.getResponseCode();

        InputStream in;
        if(status >= HttpStatus.SC_BAD_REQUEST)
        {
            result = "" + status;
            return null;
        }
        else
            in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();

        conn.disconnect();

        return new JSONObject(line);
    }*/

    class IssoRating
    {
        public int CIsso;

        public int RatingIsso;

        public long RatingDate;

        public String RatingExt;
    }

    class Isso
    {
        public int CIsso;

        public double Length;

        public String DorName;

        public double WIsso;

        public String Obstacle;

        public String Name;

        public String FullName;

        public double Latitude;

        public double Longitude;

        //public IssoRating LastRating;
    }
}
