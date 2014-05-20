DROP table users;

CREATE TABLE Reservation (
  reservation_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  reservation_day INTEGER NOT NULL,
  reservation_month INTEGER NOT NULL,
  reservation_year INTEGER NOT NULL,
  reservation_hour INTEGER NOT NULL,
  email VARCHAR(50) NOT NULL,
  CONSTRAINT chk_email CHECK (email LIKE '%@%.%')
);