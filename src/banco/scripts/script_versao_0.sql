Dados da tabela:
tipo de nota 
pessoa
vendedor - flag na tabela pessoa
condicao de pagamento 
produto 
orcamento, esta tabela deve ter uyma flag que converte em( orcamento,pedido e venda)
itensorcamento

CREATE TABLE CONDICAO_PAGAMENTO (
    CD_COND     INTEGER NOT NULL,
    DS_COND     VARCHAR(50) NOT NULL,
    CD_USUARIO  SMALLINT NOT NULL,
    DT_ALT      DATE NOT NULL,
    HR_ALT      TIME NOT NULL,
    DT_CAD      DATE NOT NULL,
    HR_CAD      TIME NOT NULL
);

CREATE TABLE ESTADO (
    CD_ESTADO   VARCHAR(2) NOT NULL,
    DS_ESTADO   VARCHAR(40) NOT NULL,
    CD_IBGE     INTEGER NOT NULL,
    CD_FILIAL   INTEGER NOT NULL,
    CD_USUARIO  SMALLINT NOT NULL,
    DT_ALT      DATE NOT NULL,
    HR_ALT      TIME NOT NULL,
    DT_CAD      DATE NOT NULL,
    HR_CAD      TIME NOT NULL
);


CREATE TABLE ESTOQUE (
    CD_FILIAL       INTEGER NOT NULL,
    CD_REF          BIGINT NOT NULL,
    QUANTIDADE      NUMERIC(16,4) NOT NULL,
    CD_USUARIO      SMALLINT NOT NULL,
    DT_CAD          DATE NOT NULL,
    DT_ALT          DATE NOT NULL,
    HR_CAD          TIME NOT NULL,
    HR_ALT          TIME NOT NULL,
    DS_LOG_ESTOQUE  VARCHAR(200),
    QT_ENTRADA      NUMERIC(16,4) DEFAULT 0 NOT NULL,
    QT_SAIDA        NUMERIC(16,4) DEFAULT 0 NOT NULL
);

CREATE TABLE FILIAL (
    CD_FILIAL        INTEGER NOT NULL,
    NM_FILIAL        VARCHAR(100) NOT NULL,
    DS_END_FIL       VARCHAR(100),
    NR_CEP_FIL       VARCHAR(8),
    DS_BAI_FIL       VARCHAR(50),
    CD_UF_FIL        VARCHAR(2) NOT NULL,
    CD_CID_FIL       VARCHAR(20),
    CD_CNPJ_FIL      VARCHAR(19),
    NR_INSC_EST_FIL  VARCHAR(20),
    CD_USUARIO       SMALLINT NOT NULL,
    DT_ALT           DATE NOT NULL,
    HR_ALT           TIME NOT NULL,
    DT_CAD           DATE NOT NULL,
    HR_CAD           TIME NOT NULL,
    TX_PIS           FLOAT NOT NULL,
    TX_COFINS        FLOAT NOT NULL
);

CREATE TABLE GRUPO (
    CD_GRUPO    INTEGER NOT NULL,
    DS_GRUPO    VARCHAR(50) NOT NULL,
    CD_USUARIO  SMALLINT NOT NULL,
    DT_ALT      DATE NOT NULL,
    HR_ALT      TIME NOT NULL,
    DT_CAD      DATE NOT NULL,
    HR_CAD      TIME NOT NULL
);

CREATE TABLE GRUPO_FISCAL (
    CD_FILIAL        SMALLINT NOT NULL,
    CD_GRUPO_FISCAL  SMALLINT NOT NULL,
    DS_GRUPO_FISCAL  VARCHAR(200),
    CD_USUARIO       SMALLINT NOT NULL,
    DT_CAD           DATE NOT NULL,
    DT_ALT           DATE NOT NULL,
    HR_CAD           TIME NOT NULL,
    HR_ALT           TIME NOT NULL
);


CREATE TABLE GRUPO_USUARIO (
    CD_GRUPO    INTEGER NOT NULL,
    DS_GRUPO    VARCHAR(50) NOT NULL,
    CD_USUARIO  SMALLINT NOT NULL,
    DT_ALT      DATE NOT NULL,
    HR_ALT      TIME NOT NULL,
    DT_CAD      DATE NOT NULL,
    HR_CAD      TIME NOT NULL,
    CD_FILIAL   INTEGER NOT NULL
);


CREATE TABLE ITEM (
    CD_FILIAL       SMALLINT NOT NULL,
    CD_MOVIMENTO    INTEGER NOT NULL,
    CD_SEQ_ITE_PRO  SMALLINT NOT NULL,
    CD_REFER_PRO    BIGINT NOT NULL,
    QT_ITE_PRO      NUMERIC(16,4) NOT NULL,
    VL_CUS_ITE_PRO  NUMERIC(16,4),
    VL_VEN_ITE_PRO  NUMERIC(16,4),
    DT_EMI_DOC      DATE,
    CD_USUARIO      SMALLINT NOT NULL,
    DT_ALT          DATE NOT NULL,
    HR_ALT          TIME NOT NULL,
    DT_CAD          DATE NOT NULL,
    HR_CAD          TIME NOT NULL
);

CREATE TABLE MARKUP (
    CD_GRUPO_FISCAL        SMALLINT NOT NULL,
    CD_ESTADO              CHAR(2) NOT NULL,
    TX_ICMS_INTERNO        NUMERIC(16,4),
    TX_ICMS_INTERESTADUAL  NUMERIC(16,4),
    CD_USUARIO             SMALLINT NOT NULL,
    DT_CAD                 DATE NOT NULL,
    DT_ALT                 DATE NOT NULL,
    HR_CAD                 TIME NOT NULL,
    HR_ALT                 TIME NOT NULL
);

CREATE TABLE MARKUPOO (
    CD_MARKUP              SMALLINT NOT NULL,
    CD_GRUPO_FISCAL        SMALLINT NOT NULL,
    CD_ESTADO              CHAR(2) NOT NULL,
    TX_ICMS_INTERNO        NUMERIC(16,4),
    TX_ICMS_INTERESTADUAL  NUMERIC(16,4),
    CD_USUARIO             SMALLINT NOT NULL,
    DT_CAD                 DATE NOT NULL,
    DT_ALT                 DATE NOT NULL,
    HR_CAD                 TIME NOT NULL,
    HR_ALT                 TIME NOT NULL
);

CREATE TABLE MUNICIPIO (
    CD_ESTADO     VARCHAR(2) NOT NULL,
    CD_MUNICIPIO  INTEGER NOT NULL,
    DS_MUNICIPIO  VARCHAR(50),
    CD_USUARIO    SMALLINT NOT NULL,
    DT_ALT        DATE NOT NULL,
    HR_ALT        TIME NOT NULL,
    DT_CAD        DATE NOT NULL,
    HR_CAD        TIME NOT NULL,
    CD_IBGE       INTEGER,
    CD_CEP        VARCHAR(20),
    CD_FILIAL     INTEGER NOT NULL
);

CREATE TABLE NCMSH (
    CD_CODIGO    INTEGER NOT NULL,
    CD_NCMSH     VARCHAR(10),
    DS_TITULO_1  BLOB SUB_TYPE 1 SEGMENT SIZE 80,
    DS_TITULO_2  BLOB SUB_TYPE 1 SEGMENT SIZE 80,
    DS_NCMSH     BLOB SUB_TYPE 1 SEGMENT SIZE 80,
    VL_MVA       NUMERIC(4,5) NOT NULL
);

CREATE TABLE ORCAMENTO (
    CD_FILIAL       SMALLINT NOT NULL,
    CD_MOVIMENTO    INTEGER NOT NULL,
    CD_VENDE        INTEGER,
    CD_PAGTO        INTEGER,
    CD_PESSOA       INTEGER,
    DT_EMI_DOC      DATE,
    DT_SAI_DOC      DATE,
    VL_TOT_CUS_DOC  NUMERIC(16,4) DEFAULT 0 NOT NULL,
    VL_TOT_PRO_DOC  NUMERIC(16,4) DEFAULT 0 NOT NULL,
    FG_SITUACAO     SMALLINT,
    CD_USUARIO      SMALLINT NOT NULL,
    DT_ALT          DATE NOT NULL,
    HR_ALT          TIME NOT NULL,
    DT_CAD          DATE NOT NULL,
    HR_CAD          TIME NOT NULL,
    CD_TIPO_DOC     INTEGER
);

CREATE TABLE PAIS (
    CD_PAIS     INTEGER NOT NULL,
    NM_PAIS     VARCHAR(100),
    CD_USUARIO  SMALLINT NOT NULL,
    DT_ALT      DATE NOT NULL,
    HR_ALT      TIME NOT NULL,
    DT_CAD      DATE NOT NULL,
    HR_CAD      TIME NOT NULL,
    CD_FILIAL   INTEGER NOT NULL
);

CREATE TABLE PARCELAS (
    CD_FILIAL              SMALLINT NOT NULL,
    CD_MOVIMENTO           INTEGER NOT NULL,
    CD_DOCUMENTO           INTEGER NOT NULL,
    NR_PARCELA             INTEGER NOT NULL,
    FG_SITUACAO            INTEGER NOT NULL,
    VL_PARCELA             NUMERIC(16,4),
    VL_JUROS               NUMERIC(16,4),
    CD_CONDICAO_PAGAMENTO  INTEGER NOT NULL,
    SEQUENCIA_PARCELA      SMALLINT NOT NULL,
    VL_ACRESCIMO           NUMERIC(16,4),
    VL_DESCONTO            NUMERIC(16,4),
    DT_VENCTO_PAR          DATE NOT NULL,
    CD_TIPO_COBRANCA       NUMERIC(3,0),
    CD_USUARIO             SMALLINT NOT NULL,
    DT_ALT                 DATE NOT NULL,
    HR_ALT                 TIME NOT NULL,
    DT_CAD                 DATE NOT NULL,
    HR_CAD                 TIME NOT NULL,
    DT_PAG_PAR             DATE,
    VL_PAGO                NUMERIC(16,4),
    CD_PESSOA              BIGINT NOT NULL
);

CREATE TABLE PESSOA (
    CD_PESSOA              INTEGER NOT NULL,
    NM_PESSOA              VARCHAR(400),
    FG_CLIENTE             SMALLINT NOT NULL,
    FG_VENDEDOR            SMALLINT NOT NULL,
    FG_FORNECEDOR          SMALLINT NOT NULL,
    DS_ENDERECO            VARCHAR(400),
    NR_ENDERECO            VARCHAR(50),
    DS_BAIRRO              VARCHAR(200),
    CD_ESTADO              VARCHAR(2),
    CD_CIDADE              INTEGER,
    CD_PAIS                INTEGER DEFAULT 1058,
    CD_CEP                 VARCHAR(8),
    DS_EMAIL               VARCHAR(200),
    NR_TELEFONE            VARCHAR(200),
    CD_CGCCPF              VARCHAR(25),
    CD_USUARIO             SMALLINT NOT NULL,
    DT_ALT                 DATE NOT NULL,
    HR_ALT                 TIME NOT NULL,
    DT_CAD                 DATE NOT NULL,
    HR_CAD                 TIME NOT NULL,
    CD_FILIAL              INTEGER NOT NULL,
    NR_INSCRICAO_ESTADUAL  VARCHAR(200),
    TIPO_CONSUMO           INTEGER,
    REGIME_TRIBUTACAO      INTEGER,
    FG_TRANSPORTADOR       SMALLINT,
    FG_ATIVO               SMALLINT
);

CREATE TABLE PRODUTO (
    CD_PROD            INTEGER NOT NULL,
    DS_PROD            VARCHAR(100) NOT NULL,
    CD_GRUPO           INTEGER NOT NULL,
    CD_SUB_GRUPO       INTEGER NOT NULL,
    FG_ATIVO           SMALLINT DEFAULT 1,
    CD_COR             INTEGER,
    CD_FABRICA         VARCHAR(40),
    CD_MARCA           INTEGER,
    DT_ALT             DATE NOT NULL,
    HR_ALT             TIME NOT NULL,
    DT_CAD             DATE NOT NULL,
    HR_CAD             TIME NOT NULL,
    CD_GP_FISCAL       SMALLINT NOT NULL,
    CD_NCM_SH          VARCHAR(11),
    CD_REF             BIGINT NOT NULL,
    CD_USUARIO         SMALLINT NOT NULL,
    CD_FILIAL          INTEGER NOT NULL,
    CD_UNIDADE_MEDIDA  INTEGER,
    QT_ESTOQUE         INTEGER NOT NULL,
    TX_IPI             FLOAT NOT NULL,
    TX_ISS             FLOAT NOT NULL
);

CREATE TABLE SUB_COND_PAG (
    CD_CONDICAO      INTEGER NOT NULL,
    CD_PARCELA       SMALLINT NOT NULL,
    NR_DIAS_PARCELA  SMALLINT NOT NULL,
    CD_COBRANCA      INTEGER NOT NULL,
    CD_USUARIO       SMALLINT NOT NULL,
    DT_ALT           DATE NOT NULL,
    HR_ALT           TIME NOT NULL,
    DT_CAD           DATE NOT NULL,
    HR_CAD           TIME NOT NULL
);

CREATE TABLE SUB_TAB_PRECO (
    CD_REF                    BIGINT NOT NULL,
    VL_CUSTO                  NUMERIC(16,4),
    VL_VENDA                  NUMERIC(16,4),
    VL_PROMOCAO               NUMERIC(16,4),
    VL_ESPECIAL               NUMERIC(16,4),
    VL_CUSTO_MED              NUMERIC(16,4),
    TX_MARGEM_LUCRO_VENDA     NUMERIC(16,4),
    TX_MARGEM_LUCRO_PROMOCAO  NUMERIC(16,4),
    TX_MARGEM_LUCRO_ESPECIAL  NUMERIC(16,4),
    CD_USUARIO                SMALLINT NOT NULL,
    DT_ALT                    DATE NOT NULL,
    HR_ALT                    TIME NOT NULL,
    DT_CAD                    DATE NOT NULL,
    HR_CAD                    TIME NOT NULL,
    CD_FILIAL                 INTEGER NOT NULL
);

CREATE TABLE TIPO_COBRANCA (
    CD_COBRANCA           INTEGER NOT NULL,
    DS_COBRANCA           VARCHAR(30) NOT NULL,
    FG_IMEDIATO           SMALLINT DEFAULT 0 NOT NULL,
    FG_CHEQUE             SMALLINT DEFAULT 0 NOT NULL,
    FG_CARTAO             SMALLINT DEFAULT 0 NOT NULL,
    CD_FILIAL             SMALLINT NOT NULL,
    CD_USUARIO            SMALLINT NOT NULL,
    DT_ALT                DATE NOT NULL,
    HR_ALT                TIME NOT NULL,
    DT_CAD                DATE NOT NULL,
    HR_CAD                TIME NOT NULL,
    FG_CREDIARIO          SMALLINT DEFAULT 0 NOT NULL,
    FG_BOLETO             SMALLINT NOT NULL,
    FG_QUITA_QUANDO_GERA  SMALLINT DEFAULT 0 NOT NULL,
    FG_ATIVO              SMALLINT NOT NULL
);

CREATE TABLE TIPO_NOTA_FISCAL (
    CD_TIPO                SMALLINT NOT NULL,
    CD_GP_FISCAL           SMALLINT NOT NULL,
    TP_CONSUMO             SMALLINT NOT NULL,
    CD_CFOP_ESTADUAL       SMALLINT,
    CD_CFOP_INTERESTADUAL  SMALLINT,
    CD_CST                 SMALLINT,
    TP_CALCULO_BASE        VARCHAR(200),
    TP_CALCULO_ICMS        VARCHAR(200),
    TP_CALCULO_BASE_I      VARCHAR(200),
    TP_CALCULO_ICMS_I      VARCHAR(200),
    DS_OBS_NF              BLOB SUB_TYPE 1 SEGMENT SIZE 80,
    CD_USUARIO             SMALLINT NOT NULL,
    DT_CAD                 DATE NOT NULL,
    DT_ALT                 DATE NOT NULL,
    HR_CAD                 TIME NOT NULL,
    HR_ALT                 TIME NOT NULL,
    TP_CALCULO_BASE_ST     VARCHAR(200),
    TP_CALCULO_ICMS_ST     VARCHAR(200),
    TP_CALCULO_BASE_I_ST   VARCHAR(200),
    TP_CALCULO_ICMS_I_ST   VARCHAR(200),
    CD_CST_COFINS          INTEGER NOT NULL,
    CD_CST_PIS             INTEGER NOT NULL,
    CD_CST_IPI             INTEGER NOT NULL
);

CREATE TABLE TIPO_NOTA (
    CD_TIPO       INTEGER NOT NULL,
    DS_TIPO_NOTA  VARCHAR(60) NOT NULL,
    CD_USUARIO    SMALLINT NOT NULL,
    DT_ALT        DATE NOT NULL,
    HR_ALT        TIME NOT NULL,
    DT_CAD        DATE NOT NULL,
    HR_CAD        TIME NOT NULL,
    CD_FILIAL     INTEGER NOT NULL
);

CREATE TABLE UNIDADE_MEDIDA (
    CD_UNIDADE  SMALLINT NOT NULL,
    DS_UNIDADE  VARCHAR(40),
    DT_ALT      DATE NOT NULL,
    DT_CAD      DATE NOT NULL,
    HR_CAD      TIME NOT NULL,
    HR_ALT      TIME NOT NULL,
    CD_USUARIO  SMALLINT NOT NULL,
    CD_FILIAL   INTEGER NOT NULL,
    DS_SIGLA    VARCHAR(20)
);

CREATE TABLE USUARIO (
    CD_USUARIO  SMALLINT NOT NULL,
    DS_LOGIN    VARCHAR(20) NOT NULL,
    DS_USUARIO  VARCHAR(35) NOT NULL,
    DS_SENHA    VARCHAR(10),
    CD_GRUPO    INTEGER NOT NULL,
    DT_ALT      DATE NOT NULL,
    HR_ALT      TIME NOT NULL,
    DT_CAD      DATE NOT NULL,
    HR_CAD      TIME NOT NULL,
    CD_FILIAL   INTEGER NOT NULL,
    FG_ATIVO    SMALLINT DEFAULT 1 NOT NULL
);