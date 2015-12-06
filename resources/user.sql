--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- Data for Name: userprincipal; Type: TABLE DATA; Schema: public; Owner: s
--

COPY userprincipal (id, email, handle, password) FROM stdin;
1	admin@admin.com	admin	\\x2432612431302463347664724a7835756d6f5055596b39714867316f755867784f36535139452f387a646e4454386e766c714e303832354d7451594f
\.


--
-- Name: userprincipal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: s
--

SELECT pg_catalog.setval('userprincipal_id_seq', 1, true);


--
-- PostgreSQL database dump complete
--

