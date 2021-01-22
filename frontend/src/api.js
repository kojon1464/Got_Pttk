import {authHeader, axios} from "./services/axios";

export const register = (email, password) =>
  axios.post("/register", {email, password});

export const login = (email, password) =>
  axios.post("/authenticate", {email, password});

export const logout = () => localStorage.setItem("auth", null);

export const getRoutes = () => axios.get("/routes", authHeader());

export const getRouteStates = routeId =>
  axios.get("/states", {params: {route_id: routeId}, ...authHeader()});

export const changeStates = state =>
  axios.post("/state-change", state, authHeader());

export const confirmStates = state =>
  axios.post("/confirm-change", state, authHeader());
