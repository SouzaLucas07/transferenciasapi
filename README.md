# 🚀 API de Transferências

API REST para gerenciamento de transferências financeiras entre usuários.

## 📋 Índice

- [Tecnologias](#tecnologias)
- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Configuração](#configuração)
- [Executando o projeto](#executando-o-projeto)
- [Endpoints](#endpoints)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Regras de Negócio](#regras-de-negócio)
- [Testes](#testes)
- [Autor](#autor)

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 21 | Linguagem de programação |
| Spring Boot | 3.3.5 | Framework principal |
| Spring Data JPA | - | Persistência de dados |
| Spring Cloud OpenFeign | 2023.0.3 | Clientes HTTP declarativos |
| PostgreSQL | - | Banco de dados relacional |
| Lombok | 1.18.36 | Redução de boilerplate |
| SpringDoc OpenAPI | 2.6.0 | Documentação Swagger |
| Maven | - | Gerenciador de dependências |

---

## ✨ Funcionalidades

- ✅ Transferência de valores entre usuários
- ✅ Validação de saldo antes da transferência
- ✅ Bloqueio de transferência para usuários lojistas
- ✅ Registro histórico de todas as transferências
- ✅ Integração com API externa de autorização
- ✅ Integração com API externa de notificação
- ✅ Documentação automática com Swagger UI
- ✅ Tratamento global de exceções
- ✅ Carregamento automático de dados iniciais

---

## 📋 Pré-requisitos

- [Java 21+](https://www.oracle.com/java/technologies/downloads/)
- [PostgreSQL 15+](https://www.postgresql.org/download/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)

---

## ⚙️ Configuração

### 1. Clone o repositório

```bash
git clone https://github.com/SouzaLucas07/transferenciasapi.git
cd transferenciasapi
