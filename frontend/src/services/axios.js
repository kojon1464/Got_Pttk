import {BACKEND_URL} from "../config";
import defAxios from "axios";

const getAuthToken = () => {
  const auth = localStorage.getItem("auth");
  return auth ? JSON.parse(auth).token : "";
};

export const authHeader = () => ({
  headers: {
    Authorization: `Bearer ${getAuthToken()}`
  }
});

export const axios = defAxios.create({
  baseURL: BACKEND_URL
});
