
# SchoolStore
Schoolstore é um sistema informatizado para gestão e venda de materiais escolares por meio da web, com foco nas instituições acadêmicas e em entidades singulares. 

## Escopo da aplicação
O sistema deverá atender as principais necessidades de uma loja virtual, incluindo: 
- Ser capaz de gerenciar a entrada e saída de produtos no estoque;
- Controlar o processo de pagamento e venda de produtos;
- Manter usuários;
- Gerar relatórios;

## Premissas do projeto
- As informações deverão ser armazenadas em uma base de dados.
- Utilizar o protocolo HTTP (Hypertext Transfer Protocol - Protocolo de Transferência de Hipertexto).
- Ser compatível com qualquer navegador.

## Objetivo do projeto
O projeto servirá como meio de avaliação para a matéria de **Laboratório de Engenharia de Software**, e também como **Trabalho de Graduação** do curso de **Análise e Desenvolvimento de Sistemas**, da **Faculdade de Tecnologia de Mogi das Cruzes** (FATEC-MC).

## Tecnologias
- Java EE 
- MySQL
- Apache Tomcat
- Selenium
- HTML5
- CSS3
- JSTL
- JQuery
- Javascript
- [MDBootstrap](https://mdbootstrap.com/)
- [Chart.js](https://www.chartjs.org/)

## Modelo Arquitetural
O sistema será desenvolvido tendo como base a arquitetura ilustrada na figura abaixo. Toda a arquitetura
será baseada nos padrões de projetos tradicionais do GoF (Gang of Four) e nos padrões J2EE sendo
executados dentro de um Servidor de Aplicações (APACHE TOMCAT).
![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/arquitetura.jpeg?raw=true)

A **VIEW** será responsável por conter os componentes da camada de apresentação, além de se comunicar com o servidor por requisição **HTTP** através do servidor de aplicação **APACHE TOMCAT**. 

Serão utilizados para esta camada as tecnologias:
 - **JSP** – *Java Server Pages*
 - **JSTL** – *JavaServer Pages Standard Template Library*
 - **HTML5** – *Hypertext Markup Language*
 - **CSS3** – *Cascading Style Sheet*
 - **MDBOOTSTRAP** – Framework CSS baseado no Material Design e Bootstrap
 - **JAVASCRIPT**
 - **JQUERY**


A classe de controle, representado no pelo componente **SERVLET** do Java, recebe a requisição e gerencia a comunicação entre a camada de apresentação e a aplicação baseado no padrão MVC.
 
 Na **VIEWHELPER**, os dados da requisição são montados de acordo com seu caso de uso.
 
 Na **COMMAND**, é identificada a operação a ser executada, levando posteriormente para o **FACADE**, onde as regras de negócio são mapeadas para determinada tarefa e então executadas.

As classes de negócio, retratado no componente **STRATEGY**, representam as
classes responsáveis por aplicar as regras de negócio do sistema. 

As classes de domínio, retratado no componente **MODEL**, são as classes que representam as entidades presentes no banco de dados, contendo somente os atributos e os métodos **GETTERS/SETTERS**. 

A camada de persistência utiliza o padrão arquitetural **DAO (*Data Access Object*)** para realizar a execução das operações dentro do banco de dados MYSQL.

O diagrama a abaixo representa a organização das classes dentro dos pacotes,
tanto da arquitetura como da aplicação que fará uso da arquitetura.

![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/arquiteturaModel.jpg?raw=true)

## Visão de Caso de Uso
#### Diagrama de Caso de Uso de visão geral do sistema
![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/dUso.jpg?raw=true)

## Visão de Lógica
Esta visão representa o diagrama do ponto de vista da organização dos pacotes do sistema nas camadas de **Apresentação**, **Negócio** e **Persistência**.

![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/Camadas.jpg?raw=true)

- **Apresentação:** Camada de apresentação acessível ao cliente por meio de interface gráfica.
- **Negócio:** Contém classes que controlam a execução das funcionalidades do sistema.
- **Persistência:** Contém classes responsáveis por persistir as entidades de modelo dentro do banco de dados relacional MYSQL.

#### 1. Camada de Apresentação
Nesta camada, haverá o pacote que contém todos os arquivos relacionados à exibição de informações para o usuário (Apresentação), o que engloba as páginas JSP e HTML, imagens, dentre outros. **Este pacote se comunica com o de controle**, onde estão as operações responsáveis pela comunicação com as
classes da camada de negócio.

![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/apresentacao.jpg?raw=true)

#### 2. Camada de Negócio
O pacote de negócio contém as classes responsáveis por controlar as regras de negócio da aplicação. O pacote domínio **(*model*)**, contém as classes que representam os domínios. O pacote controle contém as classes de controle do fluxo de informações.

![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/negocio.jpg?raw=true)

#### 3. Camada de Persistência
O pacote de persistência irá possuir o pacote **DAO**, que contém as classes e interfaces responsáveis por persistir as informações do sistema no banco de dados relacional.

![alt text](https://github.com/HenriqueZaim/Schoolstore/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/Camada%20de%20persistencia.jpg?raw=true)

## Visão de Implantação
Para a configuração em questão, é indicado os nós físicos, que executarão o sistema, e as respectivas interconexões.

![alt text](https://github.com/HenriqueZaim/Schoolstore/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/implantacao.png?raw=true)

- **Dispositivo com acesso à internet:** Nó responsável pelo acesso a aplicação via interface
- **Servidor da Aplicação:** Manterá a aplicação com as regras de negócio, classes de domínio e de gerenciamento das requisições, e persistência no banco.
- **Servidor da base de dados:** Nó que contém a base de dados central do sistema.

## Visão de Implementação
Esta visão descreve a estrutura geral de implementação, a decomposição do software em camadas de
implementação. ***A estrutura geral de implementação para o sistema é baseada na estrutura da Visão Lógica***, assim, não há necessidade de detalhar os diagramas de camadas e pacotes de implementação, uma vez que são fortemente baseados naqueles desenvolvidos para **Visão Lógica**.

## Diagramas
#### Diagrama de Classe de domínio
![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/dClasse.svg?raw=true)

#### Diagrama de Sequência
![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/dSequencia.svg?raw=true)

#### Modelo de Entidade-Relacionamento físico - Venda
![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/mFisico1.svg?raw=true)

#### Modelo de Entidade-Relacionamento físico - Usuário
![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/mFisico2.svg?raw=true)

#### Modelo de Entidade-Relacionamento Lógico
![alt text](https://github.com/HenriqueZaim/les-project/blob/master/DOCUMENTA%C3%87%C3%83O/imgs/mLogico.svg?raw=true)

## Tamanho e Performance
O sistema será usado para o controle de clientes e a gestão de vendas de produtos, por isso, poderá ter uma grande base de dados. As estimativas do número de usuários e de carga de utilização em períodos de pico de utilização, bem como questões relacionadas ao tamanho e desempenho do sistema podem ser
encontradas no **Documento de Requisitos**.

## Qualidade
Falhas eventuais na operação do sistema podem levar a prejuízos. Portanto, devem-se levar em consideração, como fatores prioritários, a usabilidade e a capacidade do sistema. Adicionalmente, o sistema pode ser alvo de ataques de pessoas mal-intencionadas, com finalidade de quebra de integridade,
confidencialidade e disponibilidade de seus dados. Para evitar que tais acontecimentos sejam bem-sucedidos, uma infraestrutura de segurança deve ser especificada e projetada.

## Cronograma Macro
Os prazos apresentados são uma estimativa inicial considerando as informações disponíveis nesta etapa do projeto. Um cronograma detalhado será elaborado na fase de planejamento e, eventualmente, estes prazos podem ser modificados.

 | O que será entregue                                 | Período    | 
 |-----------------------------------------------------|------------|
 | Proposta Técnica Comercial                          |  Semana 2  |
 | Protótipos das telas do sistema                     |  Semana 4  |
 | Apresentação de um CRUD                             |  Semana 5  |
 | Cadastro e Consulta de Vendas                       |  Semana 8  |
 | Entrega da Condução completa                        |  Semana 9  |
 | Apresentação da Condução completa                   |  Semana 12 |
 | Entrega do Documento de Visão de Projeto            |  Semana 13 |
 | Entrega da especificação do caso de uso de condução |  Semana 14 |
 | Apresentação da Condução completa                   |  Semana 16 |
 | Apresentação caso de análise                        |  Semana 18 |
 | Entrega final                                       |  Semana 20 |
 
 ## Referências
 Unified Modeling Language: http://www.omg.org/technology/documents/formal/uml.htm
RUP. Rational Unified Process.

## Licença
MIT © [Henrique Zaim](https://github.com/HenriqueZaim/Schoolstore/blob/master/LICENSE.TXT)
