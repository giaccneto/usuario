### **📋 Sistema de Gerenciamento de Usuários com Autenticação JWT**
**Um sistema completo de gerenciamento de usuários com autenticação JWT, cadastro de endereços e telefones, 
desenvolvido com Spring Boot e Spring Security.**

**🌟 Funcionalidades**
✅ Cadastro de usuários com e-mail e senha

🔐 Autenticação via JWT (JSON Web Tokens)

🏠 Associação de múltiplos endereços por usuário

📞 Cadastro de múltiplos telefones por usuário

🔄 Operações CRUD completas

🛡️ Validação de dados e tratamento de exceções

**🛠️ Tecnologias Utilizadas**
Backend: Java 17, Spring Boot 3.1.5

Segurança: Spring Security, JWT

Persistência: JPA/Hibernate

Utilitários: Lombok, MapStruct (para conversão DTO/Entity)

Build: Maven

### **🔍 Entidades Principais**
**Usuário (Usuario)**
Nome, e-mail (único) e senha (criptografada)

Relacionamento 1:N com Endereço e Telefone

Implementa UserDetails para integração com Spring Security

**Endereço (Endereco)**
Rua, número, complemento, cidade, estado e CEP

Vinculado a um usuário através de chave estrangeira

**Telefone (Telefone)**
DDD e número

Vinculado a um usuário através de chave estrangeira

### **🔒 Autenticação JWT**
O sistema utiliza JSON Web Tokens para autenticação:

Cliente envia e-mail e senha para /usuario/login

Servidor valida credenciais e retorna token JWT

Cliente envia token no header Authorization para requisições subsequentes

Validade do Token: 7 horas

### **🚀 Como Executar**
Pré-requisitos
Java 17+

Maven 3.6+

Banco de dados configurado (ex: PostgreSQL)

Configuração
Clone o repositório:

1 Clone o repositório:

git clone https://github.com/giaccneto/usuario.git

2 Configure o banco de dados em application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

3 Execute a aplicação:
mvn spring-boot:run

### **📡 Endpoints da API**
**Autenticação**
POST /usuario/login - Autentica usuário e retorna token JWT

**Usuários**
POST /usuario - Cadastra novo usuário

GET /usuario?email={email} - Busca usuário por e-mail

PUT /usuario - Atualiza dados do usuário (requer autenticação)

DELETE /usuario/{email} - Remove usuário

**Endereços**
POST /usuario/endereco - Adiciona endereço ao usuário (requer autenticação)

PUT /usuario/endereco?id={id} - Atualiza endereço

**Telefones**
POST /usuario/telefone - Adiciona telefone ao usuário (requer autenticação)

PUT /usuario/telefone?id={id} - Atualiza telefone

### **📌 Exemplo de Uso**
**Cadastro de Usuário**
<img width="772" height="447" alt="git4" src="https://github.com/user-attachments/assets/9c0f0696-b4bc-4471-8590-88012a4e2f6d" />

**Autenticação**

<img width="777" height="202" alt="git5" src="https://github.com/user-attachments/assets/2ad051c8-da36-4eac-91e6-dab92b6623cf" />

<div style="displjey inline">
  <img width='80' height='80' src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/github/github-original.svg" />
  <img width='80' height='80' src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg" />
  <img width='80' height='80' src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" />       
  <img width='80' height='80'src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg" />
  <img width='80' height='80' src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg" />
  <img width='80' height='80' src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/maven/maven-original.svg" />
</div>





