create table conta_despesa (
	id bigint auto_increment not null,	
	nome varchar(80) not null,
	valor_original double not null,
	data_vencimento datetime(6) not null,
	data_pagamento datetime(6) not null,
	primary key (id)
)engine=InnoDB;

create table atraso_pagamento (
	id bigint auto_increment not null,	
	dias_atraso int(11) not null,
	juros double not null,
	multa int(11) not null,
	conta_id bigint(20) not null,
	constraint pk_atraso_pagamento primary key(id),
  	constraint fk_atraso_pagamento FOREIGN KEY (conta_id)
  	REFERENCES conta_despesa (id)
)engine=InnoDB;