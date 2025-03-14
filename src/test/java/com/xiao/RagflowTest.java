package com.xiao;

import com.xiao.ragflow.req.dataset.CreateDatasetRequest;
import com.xiao.ragflow.req.dataset.ListDatasetsRequest;
import com.xiao.ragflow.res.dataset.CreateDatasetResponse;
import com.xiao.service.RagFlowService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RagflowTest {
    @Resource
    RagFlowService ragFlowService;
    @Test
    void testCreateKnowledge() {
        CreateDatasetRequest req = new CreateDatasetRequest();
        req.setName("java_test");
        CreateDatasetResponse dataset = ragFlowService.createDataset(req);
        System.out.println(dataset);
    }
    @Test
    void testList() {
        ListDatasetsRequest req = new ListDatasetsRequest();
        System.out.println(ragFlowService.listDatasets(req));
    }
    @Test
    void testDeleteKnowledge() {
        System.out.println(ragFlowService.deleteDatasets(List.of("bf92b2ce008011f0b81e0242ac120006")));
    }
}
