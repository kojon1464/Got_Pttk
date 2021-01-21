import TableContainer from "@material-ui/core/TableContainer";
import {Button, Typography} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import TableBody from "@material-ui/core/TableBody";
import TableHead from "@material-ui/core/TableHead";
import TableCell from "@material-ui/core/TableCell";
import TableRow from "@material-ui/core/TableRow";
import React, {useState, useEffect} from "react";
import Table from "@material-ui/core/Table";
import * as api from "../../api";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import "date-fns";
import DateFnsUtils from "@date-io/date-fns";
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider
} from "@material-ui/pickers";

const RouteDescription = ({route, onCancelRequest}) => {
  const classes = useStyles();

  const [routeStates, setRouteStates] = useState([]);
  const [selectedState, setSelectedState] = useState(null);
  useEffect(() => {
    if (route) {
      api.getRouteStates(route.route.id).then(response => {
        setRouteStates(response.data);
        setSelectedState(null);
      });
    } else {
      setRouteStates([]);
      setSelectedState(null);
    }
  }, [route]);

  const [isStateEditorDialog, setIsStateEditorDialog] = useState(false);
  const closeStateEditorDialog = () => {
    setIsStateEditorDialog(false);
    setSelectedState(null);
  };

  const [startDate, setStartDate] = useState(new Date());

  if (!route) return null;

  const renderedRows = routeStates.map(state => (
    <TableRow
      key={state.id}
      className={selectedState === state && classes.selectedStateRow}
      style={{cursor: "pointer"}}
      onClick={() => setSelectedState(state)}
    >
      <TableCell>{state.start}</TableCell>
      <TableCell>{state.end}</TableCell>
      <TableCell>{state.pointsThere}</TableCell>
      <TableCell>{state.pointsBack}</TableCell>
      <TableCell>{state.open ? "tak" : "nie"}</TableCell>
    </TableRow>
  ));

  return (
    <>
      <Typography variant="h6">Zmień stan dla odcinka:</Typography>
      <Typography variant="h6">
        ({route.route.start.name}) - ({route.route.end.name})
      </Typography>

      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Data rozpoczęcia</TableCell>
              <TableCell>Data zakończenia</TableCell>
              <TableCell>Pkt. tam</TableCell>
              <TableCell>Pkt. pow.</TableCell>
              <TableCell>Otwarty</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <>{renderedRows}</>
          </TableBody>
        </Table>
      </TableContainer>

      <div className={classes.actionButtons}>
        <Button
          onClick={() => {
            setIsStateEditorDialog(true);
            setSelectedState(null);
          }}
        >
          Dodaj nowy stan
        </Button>
        <Button
          onClick={() => setIsStateEditorDialog(true)}
          disabled={!selectedState}
        >
          Zmień wybrany
        </Button>
        <Button onClick={onCancelRequest}>Anuluj</Button>
      </div>

      <Dialog
        open={isStateEditorDialog}
        onClose={closeStateEditorDialog}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle>Wprowadź modyfikację stanu</DialogTitle>
        <DialogContent>
          <DialogContentText>
            <div>
              <Typography>Data rozpoczęcia:</Typography>
              <Typography>Data zakończenia:</Typography>
              <Typography>Punkty tam:</Typography>
              <Typography>Punkty powrót:</Typography>
              <Typography>Otwarty:</Typography>
            </div>
            <div>
              <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <KeyboardDatePicker
                  disableToolbar
                  variant="inline"
                  format="MM/dd/yyyy"
                  margin="normal"
                  value={startDate}
                  onChange={setStartDate}
                />
                <KeyboardDatePicker
                  disableToolbar
                  variant="inline"
                  format="MM/dd/yyyy"
                  margin="normal"
                  value={startDate}
                  onChange={setStartDate}
                />
              </MuiPickersUtilsProvider>
            </div>
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={closeStateEditorDialog} color="primary">
            Zmień
          </Button>
          <Button onClick={closeStateEditorDialog} color="primary" autoFocus>
            Anuluj
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

const useStyles = makeStyles({
  selectedStateRow: {
    backgroundColor: "#D1C4E9"
  },

  actionButtons: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "flex-end",
    marginTop: 20
  }
});

export default RouteDescription;
