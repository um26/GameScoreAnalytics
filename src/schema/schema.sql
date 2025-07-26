Create table if not exists players(
  id INTEGER primary key autoincrement,
  name TEXT not null,
);

create table if not exists games(
  id INTEGER primary key autoincrement,
  player_id INTEGER not null,
  score INTEGER not null,
  played_at TEXT default current_timestamp,
  foreign key (player_id) references players(id)
);