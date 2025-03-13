-- RAGFlow数据库表结构

-- 用户表
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码(加密存储)',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `role` varchar(20) NOT NULL DEFAULT 'user' COMMENT '角色(admin, user)',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态: 0-禁用,1-正常',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(64) DEFAULT NULL COMMENT '最后登录IP',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 知识库表
CREATE TABLE `knowledge_bases` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '知识库ID',
  `name` varchar(128) NOT NULL COMMENT '知识库名称',
  `description` text COMMENT '知识库描述',
  `creator_id` bigint NOT NULL COMMENT '创建者ID',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态: 0-禁用,1-正常',
  `embedding_model` varchar(64) DEFAULT NULL COMMENT '嵌入模型',
  `llm_model` varchar(64) DEFAULT NULL COMMENT 'LLM模型',
  `config` json DEFAULT NULL COMMENT '知识库配置(JSON格式)',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_creator_id` (`creator_id`),
  CONSTRAINT `fk_kb_creator` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识库表';

-- 知识库权限表
CREATE TABLE `knowledge_base_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `knowledge_base_id` bigint NOT NULL COMMENT '知识库ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `permission_type` varchar(20) NOT NULL COMMENT '权限类型: read, write, admin',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_kb_user` (`knowledge_base_id`,`user_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_kbp_kb` FOREIGN KEY (`knowledge_base_id`) REFERENCES `knowledge_bases` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_kbp_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识库权限表';

-- 文档表
CREATE TABLE `documents` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文档ID',
  `knowledge_base_id` bigint NOT NULL COMMENT '知识库ID',
  `uploader_id` bigint NOT NULL COMMENT '上传者ID',
  `title` varchar(255) NOT NULL COMMENT '文档标题',
  `description` text COMMENT '文档描述',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小(bytes)',
  `mime_type` varchar(128) DEFAULT NULL COMMENT '文件MIME类型',
  `content_hash` varchar(64) DEFAULT NULL COMMENT '内容哈希值',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态: 0-待处理,1-处理中,2-处理完成,3-处理失败',
  `vector_status` tinyint NOT NULL DEFAULT '0' COMMENT '向量化状态: 0-未向量化,1-向量化中,2-向量化完成,3-向量化失败',
  `error_message` text COMMENT '错误信息',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_kb_id` (`knowledge_base_id`),
  KEY `idx_uploader` (`uploader_id`),
  KEY `idx_status` (`status`),
  CONSTRAINT `fk_doc_kb` FOREIGN KEY (`knowledge_base_id`) REFERENCES `knowledge_bases` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_doc_uploader` FOREIGN KEY (`uploader_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文档表';

-- 文档块表
CREATE TABLE `document_chunks` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文档块ID',
  `document_id` bigint NOT NULL COMMENT '文档ID',
  `chunk_index` int NOT NULL COMMENT '块索引',
  `content` text NOT NULL COMMENT '块内容',
  `metadata` json DEFAULT NULL COMMENT '元数据(JSON格式)',
  `vector_embedding` mediumblob COMMENT '向量嵌入(二进制格式)',
  `embedding_model` varchar(64) DEFAULT NULL COMMENT '嵌入使用的模型',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_document_id` (`document_id`),
  CONSTRAINT `fk_chunk_doc` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文档块表';

-- 会话表
CREATE TABLE `conversations` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '会话ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `knowledge_base_id` bigint DEFAULT NULL COMMENT '知识库ID，可为空表示非知识库会话',
  `title` varchar(255) DEFAULT NULL COMMENT '会话标题',
  `system_prompt` text COMMENT '系统提示词',
  `model` varchar(64) DEFAULT NULL COMMENT '使用的模型',
  `temperature` decimal(3,2) DEFAULT '0.70' COMMENT '温度参数',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态: 0-已归档,1-正常',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_kb_id` (`knowledge_base_id`),
  CONSTRAINT `fk_conv_kb` FOREIGN KEY (`knowledge_base_id`) REFERENCES `knowledge_bases` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_conv_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会话表';

-- 消息表
CREATE TABLE `messages` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `conversation_id` bigint NOT NULL COMMENT '会话ID',
  `sender_type` varchar(10) NOT NULL COMMENT '发送者类型: user, assistant, system',
  `content` text NOT NULL COMMENT '消息内容',
  `tokens` int DEFAULT NULL COMMENT '消息token数量',
  `metadata` json DEFAULT NULL COMMENT '元数据(JSON格式)',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_conversation_id` (`conversation_id`),
  KEY `idx_created_at` (`created_at`),
  CONSTRAINT `fk_msg_conv` FOREIGN KEY (`conversation_id`) REFERENCES `conversations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

-- 检索结果表
CREATE TABLE `retrieval_results` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '检索结果ID',
  `message_id` bigint NOT NULL COMMENT '消息ID',
  `chunk_id` bigint NOT NULL COMMENT '文档块ID',
  `relevance_score` decimal(10,8) NOT NULL COMMENT '相关性得分',
  `ranking` int NOT NULL COMMENT '排名',
  `used_in_response` tinyint NOT NULL DEFAULT '0' COMMENT '是否用于回答: 0-否,1-是',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_message_id` (`message_id`),
  KEY `idx_chunk_id` (`chunk_id`),
  CONSTRAINT `fk_retr_chunk` FOREIGN KEY (`chunk_id`) REFERENCES `document_chunks` (`id`),
  CONSTRAINT `fk_retr_msg` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='检索结果表';

-- 反馈表
CREATE TABLE `feedbacks` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '反馈ID',
  `message_id` bigint NOT NULL COMMENT '消息ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `rating` tinyint DEFAULT NULL COMMENT '评分: 1-5',
  `feedback_text` text COMMENT '反馈内容',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_message_id` (`message_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_feed_msg` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_feed_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='反馈表';

-- 日志表
CREATE TABLE `logs` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID(可为空)',
  `action` varchar(64) NOT NULL COMMENT '动作类型',
  `entity_type` varchar(64) DEFAULT NULL COMMENT '实体类型',
  `entity_id` varchar(64) DEFAULT NULL COMMENT '实体ID',
  `details` json DEFAULT NULL COMMENT '详细信息(JSON格式)',
  `ip_address` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `success` tinyint NOT NULL DEFAULT '1' COMMENT '成功状态: 0-失败,1-成功',
  `error_message` text COMMENT '错误信息',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_action` (`action`),
  KEY `idx_entity` (`entity_type`,`entity_id`),
  KEY `idx_created_at` (`created_at`),
  CONSTRAINT `fk_log_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- API密钥表
CREATE TABLE `api_keys` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'API密钥ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `key_name` varchar(64) NOT NULL COMMENT '密钥名称',
  `api_key` varchar(128) NOT NULL COMMENT 'API密钥(加密存储)',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态: 0-禁用,1-正常',
  `last_used_at` datetime DEFAULT NULL COMMENT '最后使用时间',
  `expired_at` datetime DEFAULT NULL COMMENT '过期时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_api_key` (`api_key`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_apikey_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='API密钥表';

-- 用量统计表
CREATE TABLE `usage_statistics` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `date` date NOT NULL COMMENT '日期',
  `conversation_count` int NOT NULL DEFAULT '0' COMMENT '会话数量',
  `message_count` int NOT NULL DEFAULT '0' COMMENT '消息数量',
  `token_count` int NOT NULL DEFAULT '0' COMMENT '消耗token数量',
  `prompt_tokens` int NOT NULL DEFAULT '0' COMMENT '提示token数量',
  `completion_tokens` int NOT NULL DEFAULT '0' COMMENT '补全token数量',
  `embedding_count` int NOT NULL DEFAULT '0' COMMENT '嵌入请求数量',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_date` (`user_id`,`date`),
  CONSTRAINT `fk_usage_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用量统计表';

-- 知识库同步任务表
CREATE TABLE `sync_tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `knowledge_base_id` bigint NOT NULL COMMENT '知识库ID',
  `creator_id` bigint NOT NULL COMMENT '创建者ID',
  `task_type` varchar(20) NOT NULL COMMENT '任务类型: incremental, full',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态: 0-待执行,1-执行中,2-已完成,3-失败',
  `progress` decimal(5,2) DEFAULT '0.00' COMMENT '进度百分比',
  `total_documents` int DEFAULT '0' COMMENT '总文档数',
  `processed_documents` int DEFAULT '0' COMMENT '已处理文档数',
  `error_message` text COMMENT '错误信息',
  `started_at` datetime DEFAULT NULL COMMENT '开始时间',
  `completed_at` datetime DEFAULT NULL COMMENT '完成时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_kb_id` (`knowledge_base_id`),
  KEY `idx_creator_id` (`creator_id`),
  KEY `idx_status` (`status`),
  CONSTRAINT `fk_sync_creator` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_sync_kb` FOREIGN KEY (`knowledge_base_id`) REFERENCES `knowledge_bases` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识库同步任务表';