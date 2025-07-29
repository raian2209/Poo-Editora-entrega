# Editora Sr. Paulão

### 📋 Sumário


* [Visão Geral](#visão-geral)
* [Features](#features)
* [Regras de Negócio](#-regras-de-negócio)
* [Tecnologias Utilizadas](#-tecnologias-utilizadas)
* [Pré-requisitos](#-pré-requisitos)
* [Como Rodar o Projeto](#-como-rodar-o-projeto)
    * [Clonar o Repositório](#clonar-o-repositório)
    * [Configurar e Iniciar o Banco de Dados com Docker](#configurar-e-iniciar-o-banco-de-dados-com-docker)
    * [Corrigir a Classe Principal no pom.xml](#corrigir-a-classe-principal-no-pomxml)
    * [Compilar o Projeto com Maven](#compilar-o-projeto-com-maven)
    * [Executar a Aplicação JavaFX](#executar-a-aplicação-javafx)
* [Algumas Telas](#-algumas-telas)
* [Contribuição](#-contribuição)
* [Licença](#-licença)

# Visão Geral
Sr. Paulão é um grande empreendedor e resolveu diversificar seu business.Agora, ele vai expandir sua livraria e criar como anexo, uma editora.
Sr. Paulão não quer misturar os dados e sistemas das duas empresas. Portanto, ele deseja ter um sistema de informação para gerenciar exclusivamente sua editora.

# Features

- [X] Cadastrar, alterar ou excluir por
    - [X] Autores (Nome, endereço, cpf)
    - [X] Obras(Título, Gênero, Ano, Autor, Status (Em avaliação, Aceita ou Rejeitada))
    - [X] Avaliadores (Nome, endereço, cpf)
- [X] Buscar
    - [X] Obras por Escritor, Título, Status ou Ano
    - [X] Avaliadores  Por Obra ou Nome
    - [X] Autores por Nome ou Obra
- [X] Definir Avaliadores para as Obras
- [X] O avaliador designado pode avaliar Obras como Aceita ou Rejeitada
- [X] Gerar relatórios de Obras avaliadas em um dado período ou por um determinado avaliador

### Regras do Projeto

- Apenas o Dono Sr. Paulão pode definir avaliadores para obras e cadastrar avaliadores
- Escritores só podem visualizar suas obras
- Avaliadores só podem visualizar as obras que são responsáveis por avaliar

### Tecnologias Usadas

- Java
- Java FX
- PostgreSQL
- Docker

### Pré-requisitos

- Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina: [Java](https://www.oracle.com/java/technologies/downloads/), [Docker](https://www.docker.com/products/docker-desktop/), [Git](https://git-scm.com/downloads), uma IDE como [IntelliJ](https://www.jetbrains.com/pt-br/idea/download/other.html), [Eclipse](https://www.eclipse.org/downloads), [VsCode](https://code.visualstudio.com/Download).

### Como Rodar o Projeto


##### Primeiro, clone o repositório do projeto para o seu ambiente local:
#
```sh
git clone https://github.com/raian2209/Poo-Editora-entrega
```
```sh
cd Poo-Editora-entrega.git
```
##### Configurar e Iniciar o Banco de Dados com Docker
#
Execute o seguinte comando para iniciar os serviços do PostgreSQL e do pgAdmin:
```sh
docker-compose up -d
```

##### Corrigir a Classe Principal no pom.xml

Abra o arquivo pom.xml na raiz do projeto.
######
Procure pela seção <plugin> do javafx-maven-plugin.
######
Substitua isto:
```sh
<mainClass>org.example.demo1/main.View.HelloAplication</mainClass>
```
Por isto:
```sh
<mainClass>org.example.pooeditoraentrega/main.View.HelloApplication</mainClass>
```

##### Compilar o Projeto com Maven
#
```sh
mvn clean install
```

##### Executar a Aplicação JavaFX
#
```sh
mvn javafx:run
```

#### Algumas TelasPOO Edtitora   
