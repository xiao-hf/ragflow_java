package com.xiao.service.impl;

import com.alibaba.fastjson.JSON;
import com.xiao.ragflow.req.chat.*;
import com.xiao.ragflow.req.dataset.CreateDatasetRequest;
import com.xiao.ragflow.req.dataset.ListDatasetsRequest;
import com.xiao.ragflow.req.document.UpdateDocumentRequest;
import com.xiao.ragflow.req.retrieval.RetrievalRequest;
import com.xiao.ragflow.res.BaseResponse;
import com.xiao.ragflow.res.chat.ChatCompletionResponse;
import com.xiao.ragflow.res.chat.CreateSessionResponse;
import com.xiao.ragflow.res.chat.GetSessionMessagesResponse;
import com.xiao.ragflow.res.dataset.CreateDatasetResponse;
import com.xiao.ragflow.res.dataset.ListDatasetsResponse;
import com.xiao.ragflow.res.document.ListDocumentsResponse;
import com.xiao.ragflow.res.document.UploadDocumentResponse;
import com.xiao.ragflow.res.retrieval.RetrievalResponse;
import com.xiao.service.RagFlowService;
import com.xiao.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RAGFlow服务实现类
 */
@Slf4j
@Service
public class RagFlowServiceImpl implements RagFlowService {

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
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

    /**
     * 构建请求URL参数字符串
     */
    private String buildUrlParams(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 1) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
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
            String responseBody = httpUtil.doDelete(url + "?" + requestBody, getAuthHeaders());
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
    public BaseResponse updateDocument(String datasetId, String documentId, UpdateDocumentRequest request) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/documents/" + documentId);
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPut(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("更新文档失败", e);
            throw new RuntimeException("更新文档失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Resource downloadDocument(String datasetId, String documentId) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/documents/" + documentId);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Resource> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, Resource.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("下载文档失败", e);
            throw new RuntimeException("下载文档失败: " + e.getMessage(), e);
        }
    }

    @Override
    public ListDocumentsResponse listDocuments(String datasetId, Integer page, Integer pageSize, 
                                              String orderby, Boolean desc, String keywords, 
                                              String documentId, String documentName) {
        try {
            Map<String, String> params = new HashMap<>();
            if (page != null) {
                params.put("page", page.toString());
            }
            if (pageSize != null) {
                params.put("page_size", pageSize.toString());
            }
            if (orderby != null) {
                params.put("orderby", orderby);
            }
            if (desc != null) {
                params.put("desc", desc.toString());
            }
            if (keywords != null) {
                params.put("keywords", keywords);
            }
            if (documentId != null) {
                params.put("id", documentId);
            }
            if (documentName != null) {
                params.put("name", documentName);
            }
            
            String url = getApiUrl("/datasets/" + datasetId + "/documents");
            String responseBody = httpUtil.doGet(url, getAuthHeaders(), params);
            return JSON.parseObject(responseBody, ListDocumentsResponse.class);
        } catch (Exception e) {
            log.error("获取文档列表失败", e);
            throw new RuntimeException("获取文档列表失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse deleteDocuments(String datasetId, List<String> documentIds) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/documents");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("ids", documentIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doDelete(url + "?" + requestBody, getAuthHeaders());
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
            String responseBody = httpUtil.doDelete(url + "?" + requestBody, getAuthHeaders());
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("停止解析文档失败", e);
            throw new RuntimeException("停止解析文档失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse addChunk(String datasetId, String documentId, String content, List<String> importantKeywords) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/documents/" + documentId + "/chunks");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("content", content);
            if (importantKeywords != null) {
                requestMap.put("important_keywords", importantKeywords);
            }
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("添加分块失败", e);
            throw new RuntimeException("添加分块失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse listChunks(String datasetId, String documentId, String keywords, 
                                  Integer page, Integer pageSize, String chunkId) {
        try {
            Map<String, String> params = new HashMap<>();
            if (keywords != null) {
                params.put("keywords", keywords);
            }
            if (page != null) {
                params.put("page", page.toString());
            }
            if (pageSize != null) {
                params.put("page_size", pageSize.toString());
            }
            if (chunkId != null) {
                params.put("id", chunkId);
            }
            
            String url = getApiUrl("/datasets/" + datasetId + "/documents/" + documentId + "/chunks");
            String responseBody = httpUtil.doGet(url, getAuthHeaders(), params);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("获取分块列表失败", e);
            throw new RuntimeException("获取分块列表失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse deleteChunks(String datasetId, String documentId, List<String> chunkIds) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/documents/" + documentId + "/chunks");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("chunk_ids", chunkIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doDelete(url + "?" + requestBody, getAuthHeaders());
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("删除分块失败", e);
            throw new RuntimeException("删除分块失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse updateChunk(String datasetId, String documentId, String chunkId, 
                                   String content, List<String> importantKeywords, Boolean available) {
        try {
            String url = getApiUrl("/datasets/" + datasetId + "/documents/" + documentId + "/chunks/" + chunkId);
            Map<String, Object> requestMap = new HashMap<>();
            if (content != null) {
                requestMap.put("content", content);
            }
            if (importantKeywords != null) {
                requestMap.put("important_keywords", importantKeywords);
            }
            if (available != null) {
                requestMap.put("available", available);
            }
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doPut(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("更新分块失败", e);
            throw new RuntimeException("更新分块失败: " + e.getMessage(), e);
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
    public BaseResponse createChatAssistant(CreateChatAssistantRequest request) {
        try {
            String url = getApiUrl("/chats");
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("创建聊天助手失败", e);
            throw new RuntimeException("创建聊天助手失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse updateChatAssistant(String chatId, CreateChatAssistantRequest request) {
        try {
            String url = getApiUrl("/chats/" + chatId);
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPut(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("更新聊天助手失败", e);
            throw new RuntimeException("更新聊天助手失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse deleteChatAssistants(List<String> chatIds) {
        try {
            String url = getApiUrl("/chats");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("ids", chatIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doDelete(url + "?" + requestBody, getAuthHeaders());
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("删除聊天助手失败", e);
            throw new RuntimeException("删除聊天助手失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse listChatAssistants(Integer page, Integer pageSize, String orderby, 
                                         Boolean desc, String chatName, String chatId) {
        try {
            Map<String, String> params = new HashMap<>();
            if (page != null) {
                params.put("page", page.toString());
            }
            if (pageSize != null) {
                params.put("page_size", pageSize.toString());
            }
            if (orderby != null) {
                params.put("orderby", orderby);
            }
            if (desc != null) {
                params.put("desc", desc.toString());
            }
            if (chatName != null) {
                params.put("name", chatName);
            }
            if (chatId != null) {
                params.put("id", chatId);
            }
            
            String url = getApiUrl("/chats");
            String responseBody = httpUtil.doGet(url, getAuthHeaders(), params);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("获取聊天助手列表失败", e);
            throw new RuntimeException("获取聊天助手列表失败: " + e.getMessage(), e);
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
    public BaseResponse updateSession(String chatId, String sessionId, UpdateSessionRequest request) {
        try {
            String url = getApiUrl("/chats/" + chatId + "/sessions/" + sessionId);
            String requestBody = JSON.toJSONString(request);
            String responseBody = httpUtil.doPut(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("更新会话失败", e);
            throw new RuntimeException("更新会话失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse listSessions(String chatId, Integer page, Integer pageSize, String orderby, 
                                   Boolean desc, String sessionName, String sessionId, String userId) {
        try {
            Map<String, String> params = new HashMap<>();
            if (page != null) {
                params.put("page", page.toString());
            }
            if (pageSize != null) {
                params.put("page_size", pageSize.toString());
            }
            if (orderby != null) {
                params.put("orderby", orderby);
            }
            if (desc != null) {
                params.put("desc", desc.toString());
            }
            if (sessionName != null) {
                params.put("name", sessionName);
            }
            if (sessionId != null) {
                params.put("id", sessionId);
            }
            if (userId != null) {
                params.put("user_id", userId);
            }
            
            String url = getApiUrl("/chats/" + chatId + "/sessions");
            String responseBody = httpUtil.doGet(url, getAuthHeaders(), params);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("获取会话列表失败", e);
            throw new RuntimeException("获取会话列表失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse deleteSessions(String chatId, List<String> sessionIds) {
        try {
            String url = getApiUrl("/chats/" + chatId + "/sessions");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("ids", sessionIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doDelete(url + "?" + requestBody, getAuthHeaders());
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("删除会话失败", e);
            throw new RuntimeException("删除会话失败: " + e.getMessage(), e);
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

    @Override
    public BaseResponse listAgents(Integer page, Integer pageSize, String orderby, 
                                 Boolean desc, String agentName, String agentId) {
        try {
            Map<String, String> params = new HashMap<>();
            if (page != null) {
                params.put("page", page.toString());
            }
            if (pageSize != null) {
                params.put("page_size", pageSize.toString());
            }
            if (orderby != null) {
                params.put("orderby", orderby);
            }
            if (desc != null) {
                params.put("desc", desc.toString());
            }
            if (agentName != null) {
                params.put("name", agentName);
            }
            if (agentId != null) {
                params.put("id", agentId);
            }
            
            String url = getApiUrl("/agents");
            String responseBody = httpUtil.doGet(url, getAuthHeaders(), params);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("获取Agent列表失败", e);
            throw new RuntimeException("获取Agent列表失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse createAgentSession(String agentId, String userId, Map<String, Object> parameters, MultipartFile[] files) {
        try {
            String url = getApiUrl("/agents/" + agentId + "/sessions");
            if (userId != null) {
                url += "?user_id=" + userId;
            }
            
            // 检查是否有文件需要上传
            if (files != null && files.length > 0) {
                // 文件上传请求
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                headers.set("Authorization", "Bearer " + apiKey);
                
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                // 添加其他参数
                if (parameters != null) {
                    for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                        body.add(entry.getKey(), entry.getValue());
                    }
                }
                
                // 添加文件
                for (MultipartFile file : files) {
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
                
                return JSON.parseObject(response.getBody(), BaseResponse.class);
            } else {
                // 普通JSON请求
                String requestBody = parameters != null ? JSON.toJSONString(parameters) : "{}";
                String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
                return JSON.parseObject(responseBody, BaseResponse.class);
            }
        } catch (Exception e) {
            log.error("创建Agent会话失败", e);
            throw new RuntimeException("创建Agent会话失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse converseWithAgent(String agentId, String question, Boolean stream, 
                                        String sessionId, String userId, Boolean syncDsl, 
                                        Map<String, Object> parameters) {
        try {
            String url = getApiUrl("/agents/" + agentId + "/completions");
            
            Map<String, Object> requestMap = new HashMap<>();
            if (question != null) {
                requestMap.put("question", question);
            }
            if (stream != null) {
                requestMap.put("stream", stream);
            }
            if (sessionId != null) {
                requestMap.put("session_id", sessionId);
            }
            if (userId != null) {
                requestMap.put("user_id", userId);
            }
            if (syncDsl != null) {
                requestMap.put("sync_dsl", syncDsl);
            }
            
            // 添加其他参数
            if (parameters != null) {
                requestMap.putAll(parameters);
            }
            
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doPost(url, getAuthHeaders(), requestBody);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("与Agent交互失败", e);
            throw new RuntimeException("与Agent交互失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse listAgentSessions(String agentId, Integer page, Integer pageSize, 
                                        String orderby, Boolean desc, String sessionId, 
                                        String userId, Boolean includeDsl) {
        try {
            Map<String, String> params = new HashMap<>();
            if (page != null) {
                params.put("page", page.toString());
            }
            if (pageSize != null) {
                params.put("page_size", pageSize.toString());
            }
            if (orderby != null) {
                params.put("orderby", orderby);
            }
            if (desc != null) {
                params.put("desc", desc.toString());
            }
            if (sessionId != null) {
                params.put("id", sessionId);
            }
            if (userId != null) {
                params.put("user_id", userId);
            }
            if (includeDsl != null) {
                params.put("dsl", includeDsl.toString());
            }
            
            String url = getApiUrl("/agents/" + agentId + "/sessions");
            String responseBody = httpUtil.doGet(url, getAuthHeaders(), params);
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("获取Agent会话列表失败", e);
            throw new RuntimeException("获取Agent会话列表失败: " + e.getMessage(), e);
        }
    }

    @Override
    public BaseResponse deleteAgentSessions(String agentId, List<String> sessionIds) {
        try {
            String url = getApiUrl("/agents/" + agentId + "/sessions");
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("ids", sessionIds);
            String requestBody = JSON.toJSONString(requestMap);
            String responseBody = httpUtil.doDelete(url + "?" + requestBody, getAuthHeaders());
            return JSON.parseObject(responseBody, BaseResponse.class);
        } catch (Exception e) {
            log.error("删除Agent会话失败", e);
            throw new RuntimeException("删除Agent会话失败: " + e.getMessage(), e);
        }
    }
} 