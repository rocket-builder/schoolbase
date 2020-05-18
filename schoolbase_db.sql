-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Май 18 2020 г., 03:25
-- Версия сервера: 10.3.13-MariaDB-log
-- Версия PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `schoolbase_db`
--

-- --------------------------------------------------------

--
-- Структура таблицы `groups`
--

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `groups`
--

INSERT INTO `groups` (`id`, `teacher_id`, `title`, `color`) VALUES
(1, NULL, '1А', 'red'),
(2, NULL, '1Б', 'yellow'),
(3, NULL, '1В', 'violet'),
(4, NULL, '1Г', 'orange'),
(5, NULL, '2А', 'green'),
(6, NULL, '2Б', 'orange'),
(7, NULL, '2В', 'violet'),
(8, NULL, '2Г', 'olive'),
(9, NULL, '3А', 'olive'),
(10, NULL, '3Б', 'purple'),
(11, NULL, '3В', 'green'),
(12, NULL, '3Г', 'yellow'),
(13, NULL, '4А', 'green'),
(14, 66, '4Б', 'green'),
(15, NULL, '4В', 'purple'),
(16, NULL, '4Г', 'teal'),
(17, NULL, '5А', 'purple'),
(18, NULL, '5Б', 'purple'),
(19, NULL, '5В', 'red'),
(20, NULL, '5Г', 'purple'),
(21, NULL, '6А', 'orange'),
(22, NULL, '6Б', 'olive'),
(23, NULL, '6В', 'purple'),
(24, NULL, '6Г', 'blue'),
(25, NULL, '7А', 'orange'),
(26, NULL, '7Б', 'green'),
(27, NULL, '7В', 'orange'),
(28, NULL, '7Г', 'purple'),
(29, NULL, '8А', 'purple'),
(30, NULL, '8Б', 'orange'),
(31, NULL, '8В', 'violet'),
(32, NULL, '8Г', 'violet'),
(33, NULL, '9А', 'yellow'),
(34, NULL, '9Б', 'yellow'),
(35, NULL, '9В', 'violet'),
(36, NULL, '9Г', 'violet'),
(37, NULL, '10А', 'orange'),
(38, NULL, '10Б', 'purple'),
(39, NULL, '10В', 'olive'),
(40, NULL, '10Г', 'purple'),
(41, NULL, '11А', 'blue'),
(42, NULL, '11Б', 'orange'),
(43, NULL, '11В', 'teal'),
(44, NULL, '11Г', 'olive');

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(67),
(67),
(67),
(67),
(67);

-- --------------------------------------------------------

--
-- Структура таблицы `parent`
--

CREATE TABLE `parent` (
  `id` bigint(20) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `student_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `parent`
--

INSERT INTO `parent` (`id`, `firstname`, `job`, `middlename`, `number`, `surname`, `student_id`) VALUES
(53, 'кекслав', 'rtr', 'кекович', '+375 (23) 432 42 41', 'кеков', 49),
(54, 'кекалия', 'rtr', 'кековна', '+375 (23) 432 42 42', 'кекова', 49),
(57, 'кек', 'kek kek', 'кек', '+375 (23) 432 42 42', 'кек', 48),
(58, '', '', '', '', '', 48),
(59, 'кек', 'rtr', 'кекович', '+375 (23) 432 42 42', 'кекова', 47),
(60, 'кек', 'rtr', 'кековна', '+375 (23) 432 42 42', 'кеков', 47);

-- --------------------------------------------------------

--
-- Структура таблицы `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `avatar_path` varchar(255) DEFAULT NULL,
  `diseases` varchar(255) DEFAULT NULL,
  `family_child_count` int(11) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `gym_group` int(11) DEFAULT NULL,
  `health_group` int(11) DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `groups_id` bigint(20) NOT NULL,
  `birthday` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `student`
--

INSERT INTO `student` (`id`, `avatar_path`, `diseases`, `family_child_count`, `firstname`, `gym_group`, `health_group`, `hobby`, `middlename`, `surname`, `groups_id`, `birthday`) VALUES
(46, '/img/tools4.jpg', 'Отстутствуют', 2, 'Василий', 0, 0, 'Катамараны', 'Приднепрович', 'Катамаранов', 14, '2001-05-15 13:00:00'),
(47, '/img/1419515114_agent-007-pierce-brosnan-james-bond-pirs-brosnan.jpg', 'Шизуха', 3, 'Петр', 0, 0, 'Кикбокс', 'Кикбоксерович', 'Кикбоксеров', 14, '2000-05-18 13:00:00'),
(48, '/res/default-avatar.png', 'Сап двач', 1, 'Святослав', 2, 2, 'Двач', 'Нульчанович', 'Двачев', 14, '2000-05-18 13:00:00'),
(49, '/img/1569262259_dzhejms-bond-54.jpg', 'Так тяночку хочеца', 1, 'Славка', 4, 2, 'Сычевание', 'Алексеевич', 'Сычев', 14, '2000-05-18 13:00:00'),
(50, '/res/default-avatar.png', 'Фл Студио головного мозга', 1, 'Аблетон', 4, 2, 'Флочка', 'Флстудиевич', 'Кубейсов', 14, '2000-05-18 13:00:00'),
(63, '/res/default-avatar.png', 'я пажилой челавек', 4, 'Антон', 1, 1, 'аб даб бдаб', 'Павлович', 'Фекалис', 14, '2007-11-10 13:00:00');

-- --------------------------------------------------------

--
-- Структура таблицы `teacher`
--

CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `avatar_path` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `teacher`
--

INSERT INTO `teacher` (`id`, `avatar_path`, `firstname`, `middlename`, `number`, `surname`, `login`, `password`, `role`) VALUES
(66, NULL, 'Мария', 'Ивановна', '+375 (29) 148 81 33', 'Иванова', 'ivanovna', '47cc36d41fa3a9d2d870a264e1c3ddaad83a98de7a07e17d066946c81c3e0afa', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `role`) VALUES
(45, 'rocket', '47cc36d41fa3a9d2d870a264e1c3ddaad83a98de7a07e17d066946c81c3e0afa', 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh41v53xm83rq9vspgdjqjjsm2` (`teacher_id`);

--
-- Индексы таблицы `parent`
--
ALTER TABLE `parent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKarwf7gfuwotw1cpj7ql4sf9f2` (`student_id`);

--
-- Индексы таблицы `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo0y5627jb1cos3cd01jmxf9d2` (`groups_id`);

--
-- Индексы таблицы `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `FKh41v53xm83rq9vspgdjqjjsm2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`);

--
-- Ограничения внешнего ключа таблицы `parent`
--
ALTER TABLE `parent`
  ADD CONSTRAINT `FKarwf7gfuwotw1cpj7ql4sf9f2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Ограничения внешнего ключа таблицы `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FKo0y5627jb1cos3cd01jmxf9d2` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
