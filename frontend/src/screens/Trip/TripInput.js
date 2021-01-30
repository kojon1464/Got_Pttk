import {makeStyles} from "@material-ui/core/styles";
import {Card, TextField} from "@material-ui/core";
import React, {useState} from "react";

const TripInput = ({
  label,
  selectedItem,
  items,
  onSelectItem,
  keyExtractor,
  nameExtractor
}) => {
  const classes = useStyles();
  const [wasFocused, setWasFocused] = useState(false);
  const [inputText, setInputText] = useState("");
  const handleInputChange = event => setInputText(event.target.value);

  const filteredItems = items.filter(item =>
    nameExtractor(item).toLowerCase().includes(inputText.toLowerCase())
  );

  return (
    <>
      <div style={{position: "relative"}}>
        <TextField
          label={label}
          value={selectedItem ? nameExtractor(selectedItem) : inputText}
          onChange={handleInputChange}
          onFocus={() => setWasFocused(true)}
          disabled={!!selectedItem}
          fullWidth
        />
        {wasFocused && !selectedItem && !!filteredItems.length && (
          <Card className={classes.hintsList}>
            {filteredItems.map(item => (
              <button
                key={keyExtractor(item)}
                className={classes.hintButton}
                onClick={() => onSelectItem(item)}
              >
                {nameExtractor(item)}
              </button>
            ))}
          </Card>
        )}
      </div>
      <div className={classes.separator} />
    </>
  );
};

const useStyles = makeStyles({
  separator: {
    width: 2,
    height: 60,
    backgroundColor: "gray",
    borderRadius: 1,
    marginTop: 15
  },
  hintsList: {
    position: "absolute",
    top: "100%",
    width: "100%",
    backgroundColor: "white",
    zIndex: 2,
    paddingTop: 8,
    paddingBottom: 8
  },
  hintButton: {
    backgroundColor: "white",
    width: "100%",
    padding: 12,
    border: 0,
    textAlign: "left",
    cursor: "pointer",
    "&:hover": {
      backgroundColor: "rgba(0, 0, 0, 0.06)"
    }
  }
});

export default TripInput;
