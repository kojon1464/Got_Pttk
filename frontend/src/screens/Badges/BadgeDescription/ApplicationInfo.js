import React from "react";
import {Button, Card, Typography} from "@material-ui/core";

const ApplicationInfo = ({onSubmitRequest}) => {
  return (
    <Card
      style={{
        width: "60%",
        marginLeft: 10,
        padding: 12,
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItem: "center"
      }}
    >
      <Typography align="center" variant="subtitle1">
        Uwaga!
      </Typography>
      <Typography align="center">
        Przed wysłaniem podania o odznakę upewnij się, iż wszystkie dodatkowe
        wymagania przedstawione w opisie są spełnione
      </Typography>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          marginTop: 16
        }}
      >
        <Button color="primary" variant="contained" onClick={onSubmitRequest}>
          Wyślij podanie o odznakę
        </Button>
      </div>
    </Card>
  );
};

ApplicationInfo.propTypes = {};

export default ApplicationInfo;
