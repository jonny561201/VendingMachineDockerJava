UPDATE products SET type = 'candy' where name in ('Rolos', 'Twizzlers');
UPDATE products SET type = 'soda' where name in ('Pepsi');
UPDATE products SET type = 'chips' where name in ('Funyons', 'Fritos');


-- CREATE OR REPLACE FUNCTION LoopThroughTable()
--   RETURNS VOID
-- AS $$
-- DECLARE
--    t_curs cursor for
--       select * from products;
--    t_row products%rowtype;
-- BEGIN
--     FOR t_row in t_curs LOOP
--         update products
--             set products.type = 'candy'
--         where current of t_curs;
--     END LOOP;
-- END;
-- $$
-- LANGUAGE plpgsql;