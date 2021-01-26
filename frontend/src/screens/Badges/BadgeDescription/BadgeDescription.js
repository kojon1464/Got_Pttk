import React, {useEffect, useState} from "react";
import MedalImg from "../../../assets/medal.png";
import {makeStyles} from "@material-ui/core/styles";
import {
  Button,
  Typography,
  Divider,
  DialogContentText
} from "@material-ui/core";
import * as api from "../../../api";
import PointsCounting from "./PointsCounting";
import ApplicationInfo from "./ApplicationInfo";
import AddressForm from "./AddressForm";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";

const BadgeDescription = ({badgeId, onReturnRequest}) => {
  const classes = useStyles();

  const [badge, setBadge] = useState(null);
  useEffect(() => {
    api.getBadge(badgeId).then(response => setBadge(response.data));
  }, [badgeId]);

  const [isErrorDialog, setIsErrorDialog] = useState(false);

  console.log(badge);

  if (!badge) return null;

  const hasEnoughPoints =
    badge.points + badge.pointsExcessive >= badge.rank.points;

  const sendApplication = () =>
    api
      .sendApplication(badgeId)
      .then(() => api.getBadge(badgeId))
      .then(response => setBadge(response.data));

  const sendAddress = address =>
    api
      .sendAddress(badgeId, address)
      .then(() => api.getBadge(badgeId))
      .then(response => {setBadge(response.data); onReturnRequest()})
      .catch(() => setIsErrorDialog(true));

  return (
    <>
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
            {!badge.badgeApplication && (
              <PointsCounting
                userPoints={badge.points}
                userPointsExcessive={badge.pointsExcessive}
              />
            )}
            {badge.badgeApplication && (
              <div>
                <Typography>
                  Data złożenia: {badge.badgeApplication?.creationDate}
                </Typography>
                <Typography>
                  Data rozpatrzenia: {badge.badgeApplication?.checkDate}
                </Typography>
                {(badge.badgeApplication.checked && badge.badgeApplication.positive) && (
                      <Typography
                      variant="subtitle1"
                      style={{marginTop: 6, color: "#4CAF50"}}
                    >
                      Status: Pozytywny!
                    </Typography>
                )}  
                {(badge.badgeApplication.checked && !badge.badgeApplication.positive) && (
                      <Typography
                      variant="subtitle1"
                      style={{marginTop: 6, color: "#B20000"}}
                    >
                      Status: Negatywny!
                    </Typography>
                )}  
                {(!badge.badgeApplication.checked) && (
                      <Typography
                      variant="subtitle1"
                      style={{marginTop: 6, color: "#FFA500"}}
                    >
                      Status: Niesprawdzono!
                    </Typography>
                )}  
              </div>
            )}
            {hasEnoughPoints && !badge.badgeApplication && (
              <ApplicationInfo onSubmitRequest={sendApplication} />
            )}

            {hasEnoughPoints &&
              badge.badgeApplication &&
              !badge.badgeApplication.address && 
              badge.badgeApplication.positive && (
                <AddressForm onSubmit={sendAddress} />
              )}
          </div>

          {!hasEnoughPoints &&
            (!badge.badgeApplication || !badge.badgeApplication.positive) && (
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

      <Dialog open={isErrorDialog} onClose={() => setIsErrorDialog(false)}>
        <DialogTitle>Błąd</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Kod pocztowy nie znajduje się w polce (poprawny format 99-999)
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button id='error' color="primary" onClick={() => setIsErrorDialog(false)}>
            OK
          </Button>
        </DialogActions>
      </Dialog>
    </>
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
