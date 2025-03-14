package com.xiao.service.impl;

import com.alibaba.fastjson.JSON;
import com.xiao.ragflow.req.chat.ChatCompletionRequest;
import com.xiao.ragflow.req.chat.CreateSessionRequest;
import com.xiao.ragflow.req.chat.GetSessionMessagesRequest;
import com.xiao.ragflow.req.chat.SendMessageRequest;
import com.xiao.ragflow.req.dataset.CreateDatasetRequest;
import com.xiao.ragflow.req.dataset.ListDatasetsRequest;
import com.xiao.ragflow.req.retrieval.RetrievalRequest;
import com.xiao.ragflow.res.BaseResponse;
import com.xiao.ragflow.res.chat.ChatCompletionResponse;
import com.xiao.ragflow.res.chat.CreateSessionResponse;
import com.xiao.ragflow.res.chat.GetSessionMessagesResponse;
import com.xiao.ragflow.res.dataset.CreateDatasetResponse;
import com.xiao.ragflow.res.dataset.ListDatasetsResponse;
import com.xiao.ragflow.res.document.UploadDocumentResponse;
import com.xiao.ragflow.res.retrieval.RetrievalResponse;
import com.xiao.service.RagFlowService;
import com.xiao.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RAGFlow服务实现类
 */
@Slf4j
@Service
public class RagFlowServiceImpl implements RagFlowService {

    @Resource
    private HttpUtil httpUtil;

    @Resource
    private RestTemplate restTemplate;

    @Value("${ragflow.api.key}")
    private String apiKey;

    @Value("${ragflow.api.base-url}")
    private String baseUrl;

    @Value("${ragflow.api.version}")
    private String apiVersion;

    /**
     * 获取带认证的请求头
     */
    private Map<String, String> getAuthHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    /**
     * 获取API接口URL
     */
    private String getApiUrl(String endpoint) {
        return baseUrl + "/api/" + apiVersion + endpoint;
    }

    @Override
    public CreateDatasetResponse createDataset(CreateDatasetRequest request) {
        try {
            String url = getApiUrl("/datasets");
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, CreateDatasetResponse.class);
        } catch (Exception e) {
            log.error("创建数据集失败", e);
            throw new RuntimeException("创建数据集失败: " + e.getMessage(), e);
        }
    }

    @Override
    public ListDatasetsResponse listDatasets(ListDatasetsRequest request) {
        try {
            // 构建请求参数
            Map<String, String> params = new HashMap<>();
            if (request.getPage() != null) {
                params.put("page", request.getPage().toString());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize().toString());
            }
            if (request.getOrderby() != null) {
                params.put("orderby", request.getOrderby());
            }
            if (request.getDesc() != null) {
                params.put("desc", request.getDesc().toString());
            }
            if (request.getName() != null) {
                params.put("name", request.getName());
            }
            if (request.getId() != null) {
                params.put("id", request.getId());
            }

            String url = getApiUrl("/datasets");
            String responseBody = httpUtil.doGet(url, getAuthHeaders(), params);
            return JSON.parseObject(responseBody, ListDatasetsResponse.class);
        } catch (Exception e) {
            log.error("获取数据集列表失败", e);
            throw new RuntimeException("获取数据集列表失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse updateDataset(String datasetId, CreateDatasetRequest request) {
        try {
            String url = getApiUrl("/datasets/" + datasetId);
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPut(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("更新数据集失败", e);
            throw new RuntimeException("更新数据集失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse deleteDatasets(List<String> datasetIds) {
        try {
            String url = getApiUrl("/datasets");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("ids", datasetIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doDelete(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("删除数据集失败", e);
            throw new RuntimeException("删除数据集失败: " + e.getMessage(), e);
        }
    }

    @Override
    public UploadDocumentResponse uploadDocuments(String datasetId, MultipartFile[] files) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/documents");
            
            // 构建multipart请求
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Bearer " + apiKey);
            
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            for (MultipartFile file : files) {
                // 将每个文件添加到请求体中
                ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };
                body.add("file", resource);
            }
            
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            
            return JSON.parseObject(response.getBody(), UploadDocumentResponse.class);
        } catch (Exception e) {
            log.error("上传文档失败", e);
            throw new RuntimeException("上传文档失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse deleteDocuments(String datasetId, List<String> documentIds) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/documents");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("ids", documentIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doDelete(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("删除文档失败", e);
            throw new RuntimeException("删除文档失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse parseDocuments(String datasetId, List<String> documentIds) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/chunks");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("document_ids", documentIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("解析文档失败", e);
            throw new RuntimeException("解析文档失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse stopParsingDocuments(String datasetId, List<String> documentIds) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/chunks");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("document_ids", documentIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doDelete(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("停止解析文档失败", e);
            throw new RuntimeException("停止解析文档失败: " + e.getMessage(), e);
        }
    }

    @Override
    public RetrievalResponse retrieveData(RetrievalRequest request) {
        try {
            String url = getApiUrl("/retrieval");
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, RetrievalResponse.class);
        } catch (Exception e) {
            log.error("检索数据失败", e);
            throw new RuntimeException("检索数据失败: " + e.getMessage(), e);
        }
    }

    @Override
    public CreateSessionResponse createSession(String chatId, CreateSessionRequest request) {
        try {
            String url = getApiUrl("/chats/" + chatId + "/sessions");
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, CreateSessionResponse.class);
        } catch (Exception e) {
            log.error("创建会话失败", e);
            throw new RuntimeException("创建会话失败: " + e.getMessage(), e);
        }
    }

    @Override
    public GetSessionMessagesResponse getSessionMessages(String chatId, GetSessionMessagesRequest request) {
        try {
            // 构建请求参数
            Map<String, String> params = new HashMap<>();
            if (request.getSessionId() != null) {
                params.put("session_id", request.getSessionId());
            }
            if (request.getPage() != null) {
                params.put("page", request.getPage().toString());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize().toString());
            }

            String url = getApiUrl("/chats/" + chatId + "/messages");
            String responseBody = httpUtil.doGet(url, getAuthHeaders(), params);
            return JSON.parseObject(responseBody, GetSessionMessagesResponse.class);
        } catch (Exception e) {
            log.error("获取会话消息失败", e);
            throw new RuntimeException("获取会话消息失败: " + e.getMessage(), e);
        }
    }

    @Override
    public ChatCompletionResponse sendMessage(String chatId, SendMessageRequest request) {
        try {
            String url = getApiUrl("/chats/" + chatId + "/completions");
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, ChatCompletionResponse.class);
        } catch (Exception e) {
            log.error("发送消息失败", e);
            throw new RuntimeException("发送消息失败: " + e.getMessage(), e);
        }
    }

    @Override
    public ChatCompletionResponse chatCompletions(String chatId, ChatCompletionRequest request) {
        try {
            String url = getApiUrl("/chats_openai/" + chatId + "/chat/completions");
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, ChatCompletionResponse.class);
        } catch (Exception e) {
            log.error("聊天完成失败", e);
            throw new RuntimeException("聊天完成失败: " + e.getMessage(), e);
        }
    }
} 