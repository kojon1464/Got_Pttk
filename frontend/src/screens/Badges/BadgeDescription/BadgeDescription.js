import React, {useEffect, useState} from "react";
import MedalImg from "../../../assets/medal.png";
import {makeStyles} from "@material-ui/core/styles";
import {Button, Card, Typography, Divider} from "@material-ui/core";
import * as api from "../../../api";
import PointsCounting from "./PointsCounting";
import ApplicationInfo from "./ApplicationInfo";

const BadgeDescription = ({badgeId, onReturnRequest}) => {
  const classes = useStyles();

  const [badge, setBadge] = useState(null);
  useEffect(() => {
    api.getBadge(badgeId).then(response => setBadge(response.data));
  }, [badgeId]);

  console.log(badge);

  if (!badge) return null;

  const hasEnoughPoints =
    badge.points + badge.pointsExcessive >= badge.rank.points;

  return (
    <div className={classes.root}>
      <div>
        <img className={classes.medalImg} src={MedalImg} />
      </div>
      <div style={{width: "100%"}}>
        <div className={classes.topBox}>
          <div>
            <Typography className={classes.titles} variant="h5">
              Odznaka: {badge.rank.badge.kind}
            </Typography>
            <Typography variant="h6">Stopień: {badge.rank.rank}</Typography>
            <Typography>Opis: {badge.rank.badge.description}</Typography>
          </div>
          <div>
            <Button color="primary" onClick={onReturnRequest}>
              Powrót
            </Button>
          </div>
        </div>

        <div style={{width: "100%"}}>
          <Typography variant="h6" style={{marginTop: 20}}>
            Wymagane punkty: {badge.rank.points}
          </Typography>
          <Divider />
        </div>

        <div style={{display: "flex", marginTop: 26}}>
          <PointsCounting
            userPoints={badge.points}
            userPointsExcessive={badge.pointsExcessive}
          />
          {hasEnoughPoints && !badge.badgeApplication && (
            <ApplicationInfo onSubmitRequest={() => true} />
          )}
        </div>

        {!hasEnoughPoints && !badge.badgeApplication && (
          <Typography
            color="error"
            variant="subtitle1"
            style={{textAlign: "center", marginTop: 30}}
          >
            Niewystarczająca liczba punktów, aby złożyć podanie o odznakę!
          </Typography>
        )}

        {hasEnoughPoints && !badge.badgeApplication && (
          <Typography
            variant="subtitle1"
            style={{textAlign: "center", marginTop: 30, color: "#4CAF50"}}
          >
            Osiągnięto dostateczny próg punktowy, aby zdobyć odznakę!
          </Typography>
        )}
      </div>
    </div>
  );
};

const useStyles = makeStyles({
  root: {
    display: "flex"
  },

  medalImg: {
    width: 200,
    marginRight: 50
  },

  topBox: {
    display: "flex",
    justifyContent: "space-between",
    width: "100%"
  }
});

export default BadgeDescription;
