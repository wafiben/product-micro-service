-- V3__fix_price_column_type.sql
ALTER TABLE products ALTER COLUMN price TYPE NUMERIC(10,2) USING price::NUMERIC(10,2);