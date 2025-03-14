package com.xiao.service;

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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * 检索数据
     *
     * @param request 检索请求
     * @return 检索响应
     */
    RetrievalResponse retrieveData(RetrievalRequest request);

    /**
     * 创建聊天会话
     *
     * @param chatId 聊天助手ID
     * @param request 创建会话请求
     * @return 创建会话响应
     */
    CreateSessionResponse createSession(String chatId, CreateSessionRequest request);

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
} 