import { Resource } from "./resource";

export interface Reagent {
    id?: number;
    resourceId?: number;
    resource?: Resource;
    amount: number;
}