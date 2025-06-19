<body>
    <h1>EcommerceAzzure - Backend</h1>
    <p>API RESTful desenvolvida com Java e Spring Boot para um sistema de e-commerce, focada em segurança, escalabilidade e integração com o frontend React.</p>

   <h2>Tecnologias Utilizadas</h2>
    <ul>
        <li><strong>Java 17+</strong></li>
        <li><strong>Spring Boot 3+</strong></li>
        <li><strong>Spring Data JPA</strong></li>
        <li><strong>Banco de Dados Relacional</strong> (PostgreSQL, MySQL ou H2)</li>
        <li><strong>Spring Security</strong> (para autenticação e autorização)</li>
        <li><strong>JWT</strong> (JSON Web Token)</li>
        <li><strong>Maven</strong> (ou Gradle, dependendo da configuração)</li>
      <li><strong>SonarQube</strong> (para análise estática de código e garantia de qualidade)</li>
    </ul>

   <h2>Como Rodar Localmente</h2>
    <ol>
        <li>Clone o repositório:
            <pre>git clone https://github.com/Jpsoaresfig/EcommerceAzzure.git
cd EcommerceAzzure</pre>
        </li>
        <li>Compile o projeto:
            <pre>mvn clean install</pre>
        </li>
        <li>Execute a aplicação:
            <pre>mvn spring-boot:run</pre>
        </li>
        <li>Acesse a API no navegador ou cliente HTTP (Postman, Insomnia):
            <pre>http://localhost:8080</pre>
        </li>
    </ol>

   <h2>Segurança com Spring Security</h2>
    <p>O projeto utiliza o <strong>Spring Security</strong> para proteger os endpoints da API. A configuração básica inclui:</p>
    <ul>
        <li><strong>Autenticação via JWT</strong>: Utiliza-se um filtro personalizado para validar o token JWT enviado no cabeçalho <code>Authorization</code>.</li>
        <li><strong>Autorização baseada em roles</strong>: Define permissões específicas para diferentes endpoints da API, garantindo que apenas usuários com as roles apropriadas possam acessar determinados recursos.</li>
    </ul>

  <h2>Funcionalidades Principais</h2>
    <ul>
        <li><strong>API RESTful</strong> para gerenciamento de produtos, usuários, pedidos e categorias</li>
        <li><strong>Persistência de dados</strong> com JPA/Hibernate</li>
        <li><strong>Autenticação e autorização</strong> utilizando JWT e Spring Security</li>
        <li><strong>Validações e tratamento de exceções</strong></li>
        <li><strong>Documentação da API</strong> com Swagger/OpenAPI</li>
    </ul>

<h2>Integração com SonarQube</h2>
  <p>O projeto utiliza o <strong>SonarQube</strong> para análise estática de código, visando garantir a qualidade e segurança do código-fonte. A configuração básica inclui:</p>
  <ul>
    <li><strong>SonarQube Scanner</strong>: Ferramenta para análise do código.</li>
    <li><strong>ESLint</strong>: Configurado para gerar relatórios de linting compatíveis com o SonarQube.</li>
    <li><strong>Jest</strong>: Configurado para gerar relatórios de cobertura de testes compatíveis com o SonarQube.</li>
  </ul>
  <p>Para mais informações sobre como integrar o SonarQube em projetos React, consulte a documentação oficial: <a href="https://www.sonarsource.com/blog/clean-react-code-sonarqube/">Clean React Code with SonarQube</a>.</p>

  <h2>Próximos Passos / Melhorias Futuras</h2>
    <ul>
        <li>Implementar autenticação e autorização</li>
        <li>Documentação da API com Swagger/OpenAPI</li>
        <li>Testes unitários e de integração</li>
        <li>Melhorar tratamento de erros e logs</li>
        <li>Implementar cache para melhorar performance</li>
    </ul>

  <h2>Como Contribuir</h2>
    <ol>
        <li>Faça um fork do projeto</li>
        <li>Crie uma branch para sua feature:
            <pre>git checkout -b feature/nome-da-feature</pre>
        </li>
        <li>Faça seus commits:
            <pre>git commit -m "feat: descrição da feature"</pre>
        </li>
        <li>Envia para seu fork:
            <pre>git push origin feature/nome-da-feature</pre>
        </li>
        <li>Abra um Pull Request neste repositório</li>
    </ol>

  <h2>Licença</h2>
    <p>MIT License — veja o arquivo <code>LICENSE</code> para detalhes.</p>
</body>
</html>
