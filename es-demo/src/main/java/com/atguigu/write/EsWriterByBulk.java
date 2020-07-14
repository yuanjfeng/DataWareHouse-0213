package com.atguigu.write;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

public class EsWriterByBulk {
    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();
        HttpClientConfig config = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(config);
        JestClient client = jestClientFactory.getObject();

        Stu stu1 = new Stu("banzhang", 16L);
        Stu stu2 = new Stu("ziyang", 22L);
        Stu stu3 = new Stu("liuqian", 8L);
        Index index1 = new Index.Builder(stu1).id("1001").build();
        Index index2 = new Index.Builder(stu1).id("1002").build();
        Index index3 = new Index.Builder(stu1).id("1003").build();


        Bulk bulk = new Bulk.Builder().addAction(index1).addAction(index2).addAction(index3).defaultIndex("stu").defaultType("_doc").build();
        client.execute(bulk);
        client.close();

    }
}
