import TableContainer from "@material-ui/core/TableContainer";
import {Button, DialogContentText, Switch, Typography} from "@material-ui/core";
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
import DialogTitle from "@material-ui/core/DialogTitle";
import "date-fns";
import DateFnsUtils from "@date-io/date-fns";
import {
  KeyboardDatePicker,
  MuiPickersUtilsProvider
} from "@material-ui/pickers";
import TextField from "@material-ui/core/TextField";

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

  const [changesResData, setChangesResData] = useState(null);
  const [stateToConfirm, setStateToConfirm] = useState(null);

  const [isStateEditorDialog, setIsStateEditorDialog] = useState(false);
  const [isErrorDialog, setIsErrorDialog] = useState(false);
  const [isConfirmationDialog, setIsConfirmationDialog] = useState(false);

  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const [pointsThere, setPointsThere] = useState(null);
  const [pointsBack, setPointsBack] = useState(null);
  const [isOpened, setIsOpened] = useState(true);

  const handleStateCreatorOpen = () => {
    setIsStateEditorDialog(true);
    setSelectedState(null);
  };
  const handleStateEditorOpen = () => {
    setIsStateEditorDialog(true);

    setStartDate(mapStringToDate(selectedState.start));
    setEndDate(mapStringToDate(selectedState.end));
    setPointsThere(selectedState.pointsThere);
    setPointsBack(selectedState.pointsBack);
    setIsOpened(selectedState.open);
  };
  const closeStateEditorDialog = () => {
    setIsStateEditorDialog(false);
    setSelectedState(null);

    setStartDate(null);
    setEndDate(null);
    setPointsThere(null);
    setPointsBack(null);
    setIsOpened(true);
  };

  const handleStateChangesSave = state => {
    return api
      .changeStates(state)
      .then(response => {
        setChangesResData(response.data);
        setIsConfirmationDialog(true);
      })
      .catch(() => setIsErrorDialog(true));
  };
  const handleConfirmationSubmit = () => {
    return api.confirmStates(stateToConfirm).then(response => {
      setRouteStates(response.data);
      setIsConfirmationDialog(false);
      setChangesResData(null);
      setStateToConfirm(null);
      setSelectedState(null);
    });
  };

  const confirmStateEdit = () => {
    const newState = {
      ...selectedState,
      start: mapDateToString(startDate),
      end: mapDateToString(endDate),
      pointsThere,
      pointsBack,
      open: isOpened
    };

    setStateToConfirm(newState);
    handleStateChangesSave(newState).then(closeStateEditorDialog);
  };

  const confirmStateCreate = () => {
    const newState = {
      id: 0,
      start: mapDateToString(startDate),
      end: mapDateToString(endDate),
      pointsThere,
      pointsBack,
      open: isOpened,
      route
    };

    setStateToConfirm(newState);
    handleStateChangesSave(newState).then(closeStateEditorDialog);
  };

  if (!route) return null;

  const renderedRows = routeStates.map(state => (
    <TableRow
      key={state.id}
      className={selectedState === state ? classes.selectedStateRow : ""}
      style={{cursor: "pointer"}}
      onClick={() => setSelectedState(state)}
    >
      <TableCell>{state.start.substring(0, 10)}</TableCell>
      <TableCell>{state.end.substring(0, 10)}</TableCell>
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
        <Button onClick={handleStateCreatorOpen}>Dodaj nowy stan</Button>
        <Button onClick={handleStateEditorOpen} disabled={!selectedState}>
          Zmień wybrany
        </Button>
        <Button onClick={onCancelRequest}>Anuluj</Button>
      </div>

      <Dialog open={isStateEditorDialog} onClose={closeStateEditorDialog}>
        <DialogTitle>
          {selectedState
            ? "Wprowadź modyfikację stanu"
            : "Podaj dane nowego stanu"}
        </DialogTitle>
        <DialogContent className={classes.formContainer}>
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <KeyboardDatePicker
              label="Data rozpoczęcia"
              disableToolbar
              variant="inline"
              format="yyyy-MM-dd"
              margin="normal"
              value={startDate}
              onChange={setStartDate}
            />
          </MuiPickersUtilsProvider>
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <KeyboardDatePicker
              label="Data zakończenia"
              disableToolbar
              variant="inline"
              format="yyyy-MM-dd"
              margin="normal"
              value={endDate}
              onChange={setEndDate}
            />
          </MuiPickersUtilsProvider>
          <TextField
            label="Punkty tam"
            type="number"
            value={pointsThere}
            onChange={event => setPointsThere(event.target.value)}
          />
          <TextField
            label="Punkty powrót"
            type="number"
            value={pointsBack}
            onChange={event => setPointsBack(event.target.value)}
          />
          <div className={classes.switchBox}>
            <Typography>Otwarty:</Typography>
            <Switch
              checked={isOpened}
              onChange={event => setIsOpened(event.target.checked)}
            />
          </div>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={selectedState ? confirmStateEdit : confirmStateCreate}
            color="primary"
          >
            {selectedState ? "Zmień" : "Utwórz"}
          </Button>
          <Button onClick={closeStateEditorDialog} color="primary" autoFocus>
            Anuluj
          </Button>
        </DialogActions>
      </Dialog>

      <Dialog open={isErrorDialog} onClose={() => setIsErrorDialog(false)}>
        <DialogTitle>Błąd</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Wystąpił błąd podczas przetwarzania zmian
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button color="primary" onClick={() => setIsErrorDialog(false)}>
            OK
          </Button>
        </DialogActions>
      </Dialog>

      <Dialog
        open={isConfirmationDialog}
        onClose={() => setIsConfirmationDialog(false)}
      >
        <DialogTitle>
          Aby zastosować wprowadzone zmiany, następujące stany musiały zostać
          zmodyfikowane przez system:
        </DialogTitle>
        <DialogContent>
          <TableContainer>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Stan</TableCell>
                  <TableCell>Modyfikacja</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {changesResData?.map((row, id) => (
                  <TableRow key={id}>
                    <TableCell>
                      od {row.state.start.substring(0, 10)} do{" "}
                      {row.state.end.substring(0, 10)} Punkty(
                      {row.state.pointsThere}/{row.state.pointsBack}){" "}
                      {row.state.open ? "Otwarty" : "Zamknięty"}
                    </TableCell>
                    <TableCell>{row.message}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
          <Typography style={{marginTop: 25, textAlign: "center"}}>
            Potwierdź czy chcesz zapisać zmiany
          </Typography>
        </DialogContent>
        <DialogActions>
          <Button color="primary" onClick={handleConfirmationSubmit}>
            Potwierdź
          </Button>
          <Button onClick={() => setIsConfirmationDialog(false)}>Anuluj</Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

const mapStringToDate = text => {
  const values = text.substring(0, 10).split("-").map(Number);
  return new Date(values[0], values[1] - 1, values[2]);
};

const mapDateToString = date => {
  const fixedMonth =
    date.getMonth() < 10 ? `0${date.getMonth()}` : `${date.getMonth()}`;
  const fixedDate =
    date.getDate() < 10 ? `0${date.getDate()}` : `${date.getDate()}`;
  return `${date.getFullYear()}-${fixedMonth}-${fixedDate}`;
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
  },

  formContainer: {
    display: "flex",
    flexDirection: "column",
    paddingLeft: 150,
    paddingRight: 150
  },

  switchBox: {
    display: "flex",
    marginTop: 20,
    alignItems: "center"
  }
});

export default RouteDescription;
