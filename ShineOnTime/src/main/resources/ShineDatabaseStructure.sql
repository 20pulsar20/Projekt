CREATE TABLE Reservation (
  reservation_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  reservation_start VARCHAR2(50) NOT NULL,
  reservation_end VARCHAR2(50) NOT NULL,
  email VARCHAR2(50) NOT NULL,
  CONSTRAINT chk_email CHECK (email LIKE '%@%.%')
);