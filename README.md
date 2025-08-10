# 🚗 Desafio Técnico Júnior #1 – Cadastro e Consulta de Abastecimentos

## 📚 Contexto

Este projeto foi desenvolvido a partir de um desafio técnico júnior disponível na internet. O principal objetivo foi testar e aprimorar minhas habilidades em Java, Spring Boot e boas práticas de desenvolvimento, além de proporcionar aprendizado contínuo ao longo do processo.

O documento original do desafio pode ser acessado neste link:  
[Link para o desafio técnico](https://drive.google.com/file/d/1syA06wnmP2z_vd3Gl8DJnxbPw-EVeAin/view)

## 🎯 Objetivo

Desenvolver uma aplicação Java para cadastro e consulta de abastecimentos em um posto de combustível, utilizando API REST e persistência em banco de dados.

---

## ✨ Funcionalidades

- **CRUD de Tipos de Combustível**
    - Nome do combustível
    - Preço por litro

- **CRUD de Bombas de Combustível**
    - Nome da bomba
    - Combustível associado

- **Cadastro e Consulta de Abastecimentos**
    - Bomba utilizada
    - Data do abastecimento
    - Quantidade abastecida (litros)
    - Valor total

> **Atenção:**  
> Não há operações de _update_ e _delete_ para abastecimentos (`fuelsupply`).  
> **Motivo:** O registro de um abastecimento representa um evento histórico e imutável, fundamental para integridade e rastreabilidade dos dados.  
> Uma vez realizado, não deve ser alterado ou removido, apenas consultado.

- **Consulta de todos os dados cadastrados via API REST**
- **Persistência dos dados em banco de dados relacional**

---

## 🏗️ Diferenciais

- API RESTful com rotas GET, POST, PUT, DELETE (exceto para abastecimentos)
- Organização em camadas (_Controller_, _Service_, _Repository_)
- Testes unitários cobrindo as principais regras de negócio
- Código limpo e organizado

---

## ✅ Requisitos Atendidos

- Projeto Java estruturado com Maven
- Relacionamentos entre entidades corretamente implementados
- API HTTP para cadastro e consulta
- Testes unitários implementados

---

## 🍃 Variáveis de Ambiente

Configure as seguintes variáveis de ambiente para conexão com o banco de dados PostgreSQL:

- `DBHOST` – Host do banco de dados (ex: `localhost`)
- `DBPORT` – Porta do banco de dados (ex: `5432`)
- `DBNAME` – Nome do banco de dados
- `DBUSER` – Usuário do banco de dados
- `DBPASSWORD` – Senha do banco de dados

