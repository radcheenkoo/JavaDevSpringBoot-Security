CREATE TABLE note(
id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
title VARCHAR(250) NOT NULL CHECK(LENGTH(title) BETWEEN 3 AND 250),
content VARCHAR NOT NULL,
last_updated_date DATE NOT NULL,
created_date DATE NOT NULL
);