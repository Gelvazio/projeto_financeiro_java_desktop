
/*

select * from itens_orc_simples 


alter table orcamento_simples add cd_cupom bigint 


select * from produto_simples 
*/


insert into produto_simples (cd_prod integer NOT NULL,
  ds_prod character varying(100) NOT NULL,
  cd_grupo integer NOT NULL,
  cd_sub_grupo integer NOT NULL,
  fg_ativo smallint DEFAULT 1,
  cd_cor integer,
  cd_fabrica character varying(40),
  cd_marca integer,
  dt_alt date NOT NULL,
  hr_alt time without time zone NOT NULL,
  dt_cad date NOT NULL,
  hr_cad time without time zone NOT NULL,
  cd_gp_fiscal smallint NOT NULL,
  cd_ncm_sh character varying(11),
  cd_ref bigint NOT NULL,
  cd_usuario smallint NOT NULL,
  cd_filial integer NOT NULL,
  cd_unidade_medida integer,
  qt_estoque integer NOT NULL,
  tx_ipi double precision NOT NULL,
  tx_iss double precision NOT NULL,
  )VALUES (1,"PRODUTO DE TESTES",1,1,1,1,"COD FAB EEW",1,'28/07/2018','10:00', '28/07/2018','12:00',1,'99999999',2,1,1,1,1,10,10);
