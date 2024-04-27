CREATE TABLE `departman` (
  `departmanID` bigint NOT NULL AUTO_INCREMENT,
  `adi` varchar(255) NOT NULL,
  `tanimi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`departmanID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `calisan` (
  `calisanID` bigint NOT NULL AUTO_INCREMENT,
  `adi` varchar(255) NOT NULL,
  `soyadi` varchar(255) NOT NULL,
  `departmanID` bigint NOT NULL,
  `maas` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`calisanID`),
  KEY `FKbevgiipfpsso14xrfebgp7qtg` (`departmanID`),
  CONSTRAINT `calisan_ibfk_1` FOREIGN KEY (`departmanID`) REFERENCES `departman` (`departmanID`),
  CONSTRAINT `FKbevgiipfpsso14xrfebgp7qtg` FOREIGN KEY (`departmanID`) REFERENCES `departman` (`departmanID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `rapor` (
  `raporID` bigint NOT NULL AUTO_INCREMENT,
  `olusturmaTarihi` datetime NOT NULL,
  `raporTipi` varchar(255) NOT NULL,
  `veriler` text,
  `olusturma_tarihi` datetime NOT NULL,
  `rapor_tipi` varchar(255) NOT NULL,
  PRIMARY KEY (`raporID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `personnelmanagement`.`departman`
(`departmanID`,
`adi`,
`tanimi`)
VALUES
(<{departmanID: }>,
<{adi: }>,
<{tanimi: }>);


INSERT INTO `personnelmanagement`.`calisan`
(`calisanID`,
`adi`,
`soyadi`,
`departmanID`,
`maas`)
VALUES
(<{calisanID: }>,
<{adi: }>,
<{soyadi: }>,
<{departmanID: }>,
<{maas: }>);

INSERT INTO `personnelmanagement`.`rapor`
(`raporID`,
`olusturmaTarihi`,
`raporTipi`,
`veriler`,
`olusturma_tarihi`,
`rapor_tipi`)
VALUES
(<{raporID: }>,
<{olusturmaTarihi: }>,
<{raporTipi: }>,
<{veriler: }>,
<{olusturma_tarihi: }>,
<{rapor_tipi: }>);
