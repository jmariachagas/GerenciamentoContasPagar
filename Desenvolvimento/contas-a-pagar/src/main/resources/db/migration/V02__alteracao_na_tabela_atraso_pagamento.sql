ALTER TABLE atraso_pagamento
ADD regra_calculo integer(11) not null;

ALTER TABLE atraso_pagamento
DROP COLUMN juros;

ALTER TABLE atraso_pagamento
DROP COLUMN multa;

