/*
 * Copyright (c) 2014, Stormpath, Inc.
 * All rights reserved.
 */
package com.jeff.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Jeff Wysong
 * @since 10/1/14 11:45 AM
 */
public class ElasticSearchTrialTest {

    @Test
    public void testElasticSearch() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch_jeffwysong")
                .build();
        Client client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));


        SearchResponse response = client.prepareSearch()
//                .setTypes("accounts")
                .setQuery(QueryBuilders.termQuery("glasses", true))
                .setPostFilter(FilterBuilders.rangeFilter("age").from(25).to(75))
//                .setExplain(true)
                .execute()
                .actionGet();

        assertEquals(2502, response.getHits().getTotalHits());
        System.out.println(response.toString());

        client.close();
    }

}
