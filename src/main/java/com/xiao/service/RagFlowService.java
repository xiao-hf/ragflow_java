package com.xiao.service;

import com.xiao.ragflow.req.chat.*;
import com.xiao.ragflow.req.dataset.CreateDatasetRequest;
import com.xiao.ragflow.req.dataset.ListDatasetsRequest;
import com.xiao.ragflow.req.document.UploadDocumentRequest;
import com.xiao.ragflow.req.document.UpdateDocumentRequest;
import com.xiao.ragflow.req.retrieval.RetrievalRequest;
import com.xiao.ragflow.res.BaseResponse;
import com.xiao.ragflow.res.chat.*;
import com.xiao.ragflow.res.dataset.CreateDatasetResponse;
import com.xiao.ragflow.res.dataset.ListDatasetsResponse;
import com.xiao.ragflow.res.document.DocumentResponse;
import com.xiao.ragflow.res.document.ListDocumentsResponse;
import com.xiao.ragflow.res.document.UploadDocumentResponse;
import com.xiao.ragflow.res.retrieval.RetrievalResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * RAGFlow服务接口
 */
public interface RagFlowService {

    /**
     * 创建数据集
     *
     * @param request 创建数据集请求
     * @return 创建数据集响应
     */
    CreateDatasetResponse createDataset(CreateDatasetRequest request);

    /**
     * 获取数据集列表
     *
     * @param request 列出数据集请求
     * @return 数据集列表响应
     */
    ListDatasetsResponse listDatasets(ListDatasetsRequest request);

    /**
     * 更新数据集
     *
     * @param datasetId 数据集ID
     * @param request 更新数据集请求
     * @return 基础响应
     */
    BaseResponse updateDataset(String datasetId, CreateDatasetRequest request);

    /**
     * 删除数据集
     *
     * @param datasetIds 数据集ID列表
     * @return 基础响应
     */
    BaseResponse deleteDatasets(List<String> datasetIds);

    /**
     * 上传文档到数据集
     *
     * @param datasetId 数据集ID
     * @param files 文件列表
     * @return 上传文档响应
     */
    UploadDocumentResponse uploadDocuments(String datasetId, MultipartFile[] files);

    /**
     * 更新文档信息
     *
     * @param datasetId 数据集ID
     * @param documentId 文档ID
     * @param request 更新文档请求
     * @return 基础响应
     */
    BaseResponse updateDocument(String datasetId, String documentId, UpdateDocumentRequest request);

    /**
     * 下载文档
     *
     * @param datasetId 数据集ID
     * @param documentId 文档ID
     * @return 文档资源
     */
    Resource downloadDocument(String datasetId, String documentId);

    /**
     * 获取文档列表
     *
     * @param datasetId 数据集ID
     * @param page 页码
     * @param pageSize 每页大小
     * @param orderby 排序字段
     * @param desc 是否降序
     * @param keywords 关键词
     * @param documentId 文档ID
     * @param documentName 文档名称
     * @return 文档列表响应
     */
    ListDocumentsResponse listDocuments(String datasetId, Integer page, Integer pageSize, 
                                        String orderby, Boolean desc, String keywords, 
                                        String documentId, String documentName);

    /**
     * 删除文档
     *
     * @param datasetId 数据集ID
     * @param documentIds 文档ID列表
     * @return 基础响应
     */
    BaseResponse deleteDocuments(String datasetId, List<String> documentIds);

    /**
     * 解析文档
     *
     * @param datasetId 数据集ID
     * @param documentIds 文档ID列表
     * @return 基础响应
     */
    BaseResponse parseDocuments(String datasetId, List<String> documentIds);

    /**
     * 停止解析文档
     *
     * @param datasetId 数据集ID
     * @param documentIds 文档ID列表
     * @return 基础响应
     */
    BaseResponse stopParsingDocuments(String datasetId, List<String> documentIds);

    /**
     * 添加分块
     *
     * @param datasetId 数据集ID
     * @param documentId 文档ID
     * @param content 分块内容
     * @param importantKeywords 重要关键词
     * @return 基础响应
     */
    BaseResponse addChunk(String datasetId, String documentId, String content, List<String> importantKeywords);

    /**
     * 获取分块列表
     *
     * @param datasetId 数据集ID
     * @param documentId 文档ID
     * @param keywords 关键词
     * @param page 页码
     * @param pageSize 每页大小
     * @param chunkId 分块ID
     * @return 分块列表响应
     */
    BaseResponse listChunks(String datasetId, String documentId, String keywords, 
                            Integer page, Integer pageSize, String chunkId);

    /**
     * 删除分块
     *
     * @param datasetId 数据集ID
     * @param documentId 文档ID
     * @param chunkIds 分块ID列表
     * @return 基础响应
     */
    BaseResponse deleteChunks(String datasetId, String documentId, List<String> chunkIds);

    /**
     * 更新分块
     *
     * @param datasetId 数据集ID
     * @param documentId 文档ID
     * @param chunkId 分块ID
     * @param content 分块内容
     * @param importantKeywords 重要关键词
     * @param available 是否可用
     * @return 基础响应
     */
    BaseResponse updateChunk(String datasetId, String documentId, String chunkId, 
                            String content, List<String> importantKeywords, Boolean available);

    /**
     * 检索数据
     *
     * @param request 检索请求
     * @return 检索响应
     */
    RetrievalResponse retrieveData(RetrievalRequest request);

    /**
     * 创建聊天助手
     *
     * @param request 创建聊天助手请求
     * @return 基础响应
     */
    BaseResponse createChatAssistant(CreateChatAssistantRequest request);

    /**
     * 更新聊天助手
     *
     * @param chatId 聊天助手ID
     * @param request 更新聊天助手请求
     * @return 基础响应
     */
    BaseResponse updateChatAssistant(String chatId, CreateChatAssistantRequest request);

    /**
     * 删除聊天助手
     *
     * @param chatIds 聊天助手ID列表
     * @return 基础响应
     */
    BaseResponse deleteChatAssistants(List<String> chatIds);

    /**
     * 获取聊天助手列表
     *
     * @param page 页码
     * @param pageSize 每页大小
     * @param orderby 排序字段
     * @param desc 是否降序
     * @param chatName 聊天助手名称
     * @param chatId 聊天助手ID
     * @return 聊天助手列表响应
     */
    BaseResponse listChatAssistants(Integer page, Integer pageSize, String orderby, 
                                  Boolean desc, String chatName, String chatId);

    /**
     * 创建聊天会话
     *
     * @param chatId 聊天助手ID
     * @param request 创建会话请求
     * @return 创建会话响应
     */
    CreateSessionResponse createSession(String chatId, CreateSessionRequest request);

    /**
     * 更新聊天会话
     *
     * @param chatId 聊天助手ID
     * @param sessionId 会话ID
     * @param request 更新会话请求
     * @return 基础响应
     */
    BaseResponse updateSession(String chatId, String sessionId, UpdateSessionRequest request);

    /**
     * 获取聊天会话列表
     *
     * @param chatId 聊天助手ID
     * @param page 页码
     * @param pageSize 每页大小
     * @param orderby 排序字段
     * @param desc 是否降序
     * @param sessionName 会话名称
     * @param sessionId 会话ID
     * @param userId 用户ID
     * @return 会话列表响应
     */
    BaseResponse listSessions(String chatId, Integer page, Integer pageSize, String orderby, 
                            Boolean desc, String sessionName, String sessionId, String userId);

    /**
     * 删除聊天会话
     *
     * @param chatId 聊天助手ID
     * @param sessionIds 会话ID列表
     * @return 基础响应
     */
    BaseResponse deleteSessions(String chatId, List<String> sessionIds);

    /**
     * 获取会话消息
     *
     * @param chatId 聊天助手ID
     * @param request 获取会话消息请求
     * @return 获取会话消息响应
     */
    GetSessionMessagesResponse getSessionMessages(String chatId, GetSessionMessagesRequest request);

    /**
     * 发送消息
     *
     * @param chatId 聊天助手ID
     * @param request 发送消息请求
     * @return 聊天完成响应
     */
    ChatCompletionResponse sendMessage(String chatId, SendMessageRequest request);

    /**
     * 使用OpenAI兼容接口完成聊天
     *
     * @param chatId 聊天ID
     * @param request 聊天完成请求
     * @return 聊天完成响应
     */
    ChatCompletionResponse chatCompletions(String chatId, ChatCompletionRequest request);

    /**
     * 获取Agent列表
     *
     * @param page 页码
     * @param pageSize 每页大小
     * @param orderby 排序字段
     * @param desc 是否降序
     * @param agentName Agent名称
     * @param agentId Agent ID
     * @return Agent列表响应
     */
    BaseResponse listAgents(Integer page, Integer pageSize, String orderby, 
                          Boolean desc, String agentName, String agentId);

    /**
     * 创建Agent会话
     *
     * @param agentId Agent ID
     * @param userId 用户ID
     * @param parameters 参数
     * @param files 文件列表
     * @return Agent会话响应
     */
    BaseResponse createAgentSession(String agentId, String userId, Map<String, Object> parameters, MultipartFile[] files);

    /**
     * 与Agent交互
     *
     * @param agentId Agent ID
     * @param question 问题
     * @param stream 是否流式返回
     * @param sessionId 会话ID
     * @param userId 用户ID
     * @param syncDsl 是否同步DSL
     * @param parameters 其他参数
     * @return 交互响应
     */
    BaseResponse converseWithAgent(String agentId, String question, Boolean stream, 
                                 String sessionId, String userId, Boolean syncDsl, 
                                 Map<String, Object> parameters);

    /**
     * 获取Agent会话列表
     *
     * @param agentId Agent ID
     * @param page 页码
     * @param pageSize 每页大小
     * @param orderby 排序字段
     * @param desc 是否降序
     * @param sessionId 会话ID
     * @param userId 用户ID
     * @param includeDsl 是否包含DSL
     * @return Agent会话列表响应
     */
    BaseResponse listAgentSessions(String agentId, Integer page, Integer pageSize, 
                                 String orderby, Boolean desc, String sessionId, 
                                 String userId, Boolean includeDsl);

    /**
     * 删除Agent会话
     *
     * @param agentId Agent ID
     * @param sessionIds 会话ID列表
     * @return 基础响应
     */
    BaseResponse deleteAgentSessions(String agentId, List<String> sessionIds);
}