import React, {useState, useEffect} from "react";
import * as api from "../../api";
import {Grid, Container} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import BadgeCard from "./BadgeCard";

const Badges = () => {
  const classes = useStyles();

  const [badges, setBadges] = useState([]);
  useEffect(() => {
    api.getBadges().then(response => setBadges(response.data));
  }, []);

  console.log(badges);

  return (
    <Container className={classes.root} maxWidth="lg">
      <Grid container spacing={2}>
        {badges.map(badge => (
          <Grid key={badge.id} item xs={4} md={3}>
            <BadgeCard badge={badge} />
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

const useStyles = makeStyles({
  root: {
    marginTop: 80
  }
});

export default Badges;
