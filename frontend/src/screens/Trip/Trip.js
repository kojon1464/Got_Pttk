import {makeStyles} from "@material-ui/core/styles";
import React, {useState, useEffect} from "react";
import {Container} from "@material-ui/core";
import TripsList from "./TripsList";
import * as api from "../../api";

const Trip = () => {
  const classes = useStyles();

  const [trips, setTrips] = useState([]);
  useEffect(() => {
    api.getTrips().then(res => setTrips(res.data));
  }, []);

  return (
    <Container className={classes.root} maxWidth="lg">
      <TripsList trips={trips} />
      <div>wefw</div>
    </Container>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex",
    marginTop: 80
  },

  tripsList: {
    display: "flex",
    flexDirection: "column",
    marginRight: 100
  },
  tripsTitle: {
    marginBottom: 15
  },
  tripButton: {
    width: "100%",
    paddingTop: 10,
    paddingBottom: 10,
    cursor: "pointer"
  }
});

export default Trip;
