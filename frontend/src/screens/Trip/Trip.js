import {Button, Container, TextField, Typography} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import React, {useState, useEffect} from "react";
import {AddRounded} from "@material-ui/icons";
import TripsList from "./TripsList";
import * as api from "../../api";
import TripInput from "./TripInput";

const Trip = () => {
  const classes = useStyles();

  const [trips, setTrips] = useState([]);
  const [localizations, setLocalizations] = useState([]);
  useEffect(() => {
    api.getTrips().then(res => setTrips(res.data));
    api.getLocalizations().then(res => setLocalizations(res.data));
    api.getRoutes().then(res => console.log(res.data));
  }, []);

  const [newTrip, setNewTrip] = useState({
    date: new Date(),
    name: "",
    description: ""
  });
  const [startLocalization, setStartLocalization] = useState(null);
  const [segments, setSegments] = useState([null]);
  const pushSegment = () => setSegments(prev => [...prev, null]);
  const handleSegmentChange = (index, newSegment) =>
    setSegments(prev => {
      prev[index] = newSegment;
      return [...prev];
    });

  const [matchingRoutes, setMatchingRoutes] = useState([]);
  const fetchMatchingRoutes = (endId, routeIndex) => {
    return api.getMatchingRoutes(endId).then(res =>
      setMatchingRoutes(prev => {
        prev[routeIndex] = res.data;
        return [...prev];
      })
    );
  };

  return (
    <Container className={classes.root} maxWidth="lg">
      <TripsList trips={trips} />
      <div>
        <Typography className={classes.title} variant="h6">
          Nowa wycieczka
        </Typography>
        <div className={classes.tripCreator}>
          <TripInput
            label="Punkt startowy"
            items={localizations}
            onSelectItem={item => {
              setStartLocalization(item);
              fetchMatchingRoutes(item.id, 0);
            }}
            selectedItem={startLocalization}
            keyExtractor={item => item.id}
            nameExtractor={item => item.name}
          />
          {segments.map((segment, index) => (
            <TripInput
              label={`Punkt nr. ${index + 2}`}
              items={matchingRoutes[index] || []}
              onSelectItem={item => {
                handleSegmentChange(index, item);
                fetchMatchingRoutes(item.route.end.id, index + 1);
              }}
              selectedItem={segment}
              keyExtractor={item => item.route.id}
              nameExtractor={item => item.route.end.name}
            />
          ))}

          <Button startIcon={<AddRounded />} onClick={pushSegment}>
            Dodaj następny
          </Button>
        </div>
      </div>
    </Container>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex",
    marginTop: 80,
    marginBottom: 150
  },

  title: {
    marginBottom: 15
  },

  tripCreator: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    width: 300
  }
});

export default Trip;
