BEGIN;

CREATE TABLE IF NOT EXISTS recipe (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    type text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS recipe_component (
    id serial NOT NULL PRIMARY KEY,
    recipe_id integer NOT NULL,
    ingredient_id integer NOT NULL,
    amount numeric NOT NULL,
    unit text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredient (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS process (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    recipe_id integer NOT NULL PRIMARY KEY,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS process_step (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text,
    process_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS ferment (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text,
    recipe_id integer NOT NULL,
    vessel_id integer NOT NULL,
    original_gravity numeric NOT NULL,
    temperature numeric NOT NULL,
    start_date timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS vessel (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text,
    volume numeric NOT NULL,
    unit text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS log (
    id serial NOT NULL PRIMARY KEY,
    notes text,
    vessel_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS ferment_log_entry (
    id serial NOT NULL PRIMARY KEY,
    temperature numeric NOT NULL,
    notes text,
    specific_gravity numeric NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS distillation_log_entry (
    id serial NOT NULL PRIMARY KEY,
    temperature numeric NOT NULL,
    notes text,
    abv numeric NOT NULL,
    volume numeric NOT NULL,
    unit text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

ALTER TABLE IF EXISTS recipe_component ADD CONSTRAINT recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id);
ALTER TABLE IF EXISTS recipe_component ADD CONSTRAINT ingredient_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient (id);

ALTER TABLE IF EXISTS process ADD CONSTRAINT recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE IF EXISTS process_step ADD CONSTRAINT process_fk FOREIGN KEY (process_id) REFERENCES process (id);

ALTER TABLE IF EXISTS ferment ADD CONSTRAINT recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE IF EXISTS ferment ADD CONSTRAINT vessel_fk FOREIGN KEY (vessel_id) REFERENCES vessel (id);

ALTER TABLE IF EXISTS log ADD CONSTRAINT vessel_fk FOREIGN KEY (vessel_id) REFERENCES vessel (id);

COMMIT;
