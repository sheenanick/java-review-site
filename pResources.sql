--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: resources; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE resources (
    id integer NOT NULL,
    title character varying,
    url character varying,
    description character varying,
    techid integer,
    avgrating integer
);


ALTER TABLE resources OWNER TO "Guest";

--
-- Name: resources_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE resources_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE resources_id_seq OWNER TO "Guest";

--
-- Name: resources_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE resources_id_seq OWNED BY resources.id;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE reviews (
    id integer NOT NULL,
    title character varying,
    review text,
    rating integer,
    reviewer character varying,
    email character varying,
    resourceid integer,
    date timestamp without time zone DEFAULT now()
);


ALTER TABLE reviews OWNER TO "Guest";

--
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE reviews_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE reviews_id_seq OWNER TO "Guest";

--
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE reviews_id_seq OWNED BY reviews.id;


--
-- Name: technologies; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE technologies (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE technologies OWNER TO "Guest";

--
-- Name: technologies_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE technologies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE technologies_id_seq OWNER TO "Guest";

--
-- Name: technologies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE technologies_id_seq OWNED BY technologies.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY resources ALTER COLUMN id SET DEFAULT nextval('resources_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY reviews ALTER COLUMN id SET DEFAULT nextval('reviews_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY technologies ALTER COLUMN id SET DEFAULT nextval('technologies_id_seq'::regclass);


--
-- Data for Name: resources; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY resources (id, title, url, description, techid, avgrating) FROM stdin;
1	Oracle Documentation	https://docs.oracle.com/javase/7/docs/api/	\N	9	\N
2	Official Source	http://api.jquery.com/	\N	18	\N
\.


--
-- Name: resources_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('resources_id_seq', 2, true);


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY reviews (id, title, review, rating, reviewer, email, resourceid, date) FROM stdin;
2	It sucks!	I can't find anything	1	noneofyourbusiness	nyb@sample.com	1	2016-09-21 15:37:04.133124
3	The Best	This is the best site ever, it taught me everything I know	5	know-it-all	kia@sample.com	1	2016-09-21 15:44:08.506528
4	OFFICIAL SOURCE	You can't beat the official source, the answers may be hard to find, but they are always right!	4	Sun O'Riley	sun@oracle.com	1	2016-09-21 15:54:56.063985
5	Hard to read	They really need to increase their font size.	2	nearsighted	tester@39dollarglasses.com	1	2016-09-21 16:14:05.246871
6	literally the worst	I thought the Mozilla was bad but I never knew bad organization until I looked at the oracle documentation. 	0	me	nope	1	2016-09-21 16:18:18.326221
7	I like it	Easy to find things. Good examples. A++ would look up again	17	me	nope	2	2016-09-21 16:30:48.061353
\.


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('reviews_id_seq', 7, true);


--
-- Data for Name: technologies; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY technologies (id, name) FROM stdin;
9	Java
10	PHP
11	Ruby
12	Spark Framework
13	Spark
14	C#
15	Python
16	R
17	HTML
18	jQuery
\.


--
-- Name: technologies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('technologies_id_seq', 18, true);


--
-- Name: resources_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT resources_pkey PRIMARY KEY (id);


--
-- Name: reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- Name: technologies_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY technologies
    ADD CONSTRAINT technologies_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

