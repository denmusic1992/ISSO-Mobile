package com.ais.admin.isso_s;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;


public class MarshalDouble
{
    public void writeInstance(XmlSerializer writer, Object obj) throws IOException {
        writer.text(obj.toString());
    }

}

