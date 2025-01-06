import { AuthControllerApi } from "./generated/api";
import { Configuration } from "./generated/configuration";

const config = new Configuration({
    basePath: 'http://localhost:8080'
})

const apiClient = {
    auth: new AuthControllerApi(config)
}

export default apiClient;
export * from './generated/index';