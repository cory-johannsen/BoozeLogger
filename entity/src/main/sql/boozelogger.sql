BEGIN;

CREATE TABLE IF NOT EXISTS recipe (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    type text NOT NULL,
    process_id integer,
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
    recipe_id integer NOT NULL,
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
    original_gravity numeric NOT NULL,
    temperature numeric NOT NULL,
    start_date timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS distillation (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text,
    ferment_id integer NOT NULL,
    specific_gravity numeric NOT NULL,
    volume numeric NOT NULL,
    unit text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS finish (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text,
    volume numeric NOT NULL,
    unit text NOT NULL,
    ferment_id integer NOT NULL,
    distillation_id integer,
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

CREATE TABLE IF NOT EXISTS ferment_vessel (
    ferment_id integer NOT NULL,
    vessel_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    PRIMARY KEY (ferment_id, vessel_id)
);

CREATE TABLE IF NOT EXISTS distillation_vessel (
    distillation_id integer NOT NULL,
    vessel_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    PRIMARY KEY (distillation_id, vessel_id)
);

CREATE TABLE IF NOT EXISTS finish_vessel (
    finish_id integer NOT NULL,
    vessel_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    PRIMARY KEY (finish_id, vessel_id)
);

CREATE TABLE IF NOT EXISTS ferment_log (
    id serial NOT NULL PRIMARY KEY,
    notes text,
    vessel_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS distillation_log (
    id serial NOT NULL PRIMARY KEY,
    notes text,
    vessel_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS finish_log (
    id serial NOT NULL PRIMARY KEY,
    notes text,
    vessel_id integer NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS ferment_log_entry (
    id serial NOT NULL PRIMARY KEY,
    ferment_log_id integer NOT NULL,
    temperature numeric NOT NULL,
    notes text,
    specific_gravity numeric NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS distillation_log_entry (
    id serial NOT NULL PRIMARY KEY,
    distillation_log_id integer NOT NULL,
    temperature numeric NOT NULL,
    notes text,
    abv numeric NOT NULL,
    volume numeric NOT NULL,
    unit text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS finish_log_entry (
    id serial NOT NULL PRIMARY KEY,
    finish_log_id integer NOT NULL,
    temperature numeric NOT NULL,
    notes text,
    flavor text NOT NULL,
    color text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);

ALTER TABLE IF EXISTS recipe ADD CONSTRAINT process_fk FOREIGN KEY (process_id) REFERENCES process (id);

ALTER TABLE IF EXISTS recipe_component ADD CONSTRAINT recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id);
ALTER TABLE IF EXISTS recipe_component ADD CONSTRAINT ingredient_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient (id);

ALTER TABLE IF EXISTS process ADD CONSTRAINT recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE IF EXISTS process_step ADD CONSTRAINT process_fk FOREIGN KEY (process_id) REFERENCES process (id);

ALTER TABLE IF EXISTS ferment ADD CONSTRAINT recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE IF EXISTS distillation ADD CONSTRAINT ferment_fk FOREIGN KEY (ferment_id) REFERENCES ferment (id);

ALTER TABLE IF EXISTS finish ADD CONSTRAINT ferment_fk FOREIGN KEY (ferment_id) REFERENCES ferment (id);
ALTER TABLE IF EXISTS finish ADD CONSTRAINT distillation_fk FOREIGN KEY (distillation_id) REFERENCES distillation (id);

ALTER TABLE IF EXISTS ferment_vessel ADD CONSTRAINT ferment_fk FOREIGN KEY (ferment_id) REFERENCES ferment (id);
ALTER TABLE IF EXISTS ferment_vessel ADD CONSTRAINT vessel_fk FOREIGN KEY (vessel_id) REFERENCES vessel (id);

ALTER TABLE IF EXISTS distillation_vessel ADD CONSTRAINT distillation_fk FOREIGN KEY (distillation_id) REFERENCES distillation (id);
ALTER TABLE IF EXISTS distillation_vessel ADD CONSTRAINT vessel_fk FOREIGN KEY (vessel_id) REFERENCES vessel (id);

ALTER TABLE IF EXISTS finish_vessel ADD CONSTRAINT finish_fk FOREIGN KEY (finish_id) REFERENCES finish (id);
ALTER TABLE IF EXISTS finish_vessel ADD CONSTRAINT vessel_fk FOREIGN KEY (vessel_id) REFERENCES vessel (id);

ALTER TABLE IF EXISTS ferment_log ADD CONSTRAINT vessel_fk FOREIGN KEY (vessel_id) REFERENCES vessel (id);

ALTER TABLE IF EXISTS distillation_log ADD CONSTRAINT vessel_fk FOREIGN KEY (vessel_id) REFERENCES vessel (id);

ALTER TABLE IF EXISTS finish_log ADD CONSTRAINT vessel_fk FOREIGN KEY (vessel_id) REFERENCES vessel (id);

ALTER TABLE IF EXISTS ferment_log_entry ADD CONSTRAINT ferment_log_fk FOREIGN KEY (ferment_log_id) REFERENCES ferment_log (id);

ALTER TABLE IF EXISTS distillation_log_entry ADD CONSTRAINT distillation_log_fk FOREIGN KEY (distillation_log_id) REFERENCES distillation_log (id);

ALTER TABLE IF EXISTS finish_log_entry ADD CONSTRAINT finish_log_fk FOREIGN KEY (finish_log_id) REFERENCES finish_log (id);

COMMIT;
