package com.atguigu.hive.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ExplodeJSONArray extends GenericUDTF{
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add("action");
        List<ObjectInspector> fieldOIs = new ArrayList<>();
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);


        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldOIs);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        if (args.length <= 0){
            return;
        }
        if (args[0] == null){
            return;
        }
        String input = args[0].toString();
        JSONArray jsonArray = new JSONArray(input);
        for (int i = 0; i < jsonArray.length(); i++) {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add(jsonArray.getString(i));
            forward(objects);

        }




    }

    @Override
    public void close() throws HiveException {

    }
}
