package com.atguigu.write;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

public class EsWrite {
    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig con = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(con);
        JestClient jestClient = jestClientFactory.getObject();
        Stu yuanyuan = new Stu("yuanyuan", 18L);
        Index index = new Index.Builder(yuanyuan).index("stu").type("_doc").id("1001").build();
        jestClient.execute(index);
        jestClient.close();
    }
}
