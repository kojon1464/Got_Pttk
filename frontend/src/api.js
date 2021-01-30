import {authHeader, axios} from "./services/axios";

export const register = (email, password) =>
  axios.post("/register", {email, password});

export const login = (email, password) =>
  axios.post("/authenticate", {email, password});

export const logout = () => localStorage.setItem("auth", null);

export const getRoutes = () => axios.get("/routes", authHeader());

export const getMatchingRoutes = endId =>
  axios.get("/routes", {...authHeader(), params: {id: endId}});

export const getRouteStates = routeId =>
  axios.get("/states", {params: {route_id: routeId}, ...authHeader()});

export const changeStates = state =>
  axios.post("/state-change", state, authHeader());

export const confirmStates = state =>
  axios.post("/confirm-change", state, authHeader());

export const getBadges = () => axios.get("/badges-to-collect", authHeader());

export const getBadge = badgeId =>
  axios.get("/badge", {...authHeader(), params: {id: badgeId}});

export const sendApplication = badgeId =>
  axios.get("/create-application", {...authHeader(), params: {id: badgeId}});

export const sendAddress = (badgeId, address) =>
  axios.post("/address", {rank: {id: badgeId}, address}, authHeader());

export const getTrips = () => axios.get("/trips", authHeader());

export const getLocalizations = () => axios.get("/localizations", authHeader());

export const saveTrip = trip => axios.post("/save-trip", trip, authHeader());

export const countPoints = trip => axios.post("/points", trip, authHeader());
