import { CraftingStat } from "./crafting-stat";
import { Reagent } from "./reagent";

export interface Recipe {
    id?: number;
    name: string;
    difficulty: number;
    requiredReagents?: Reagent[];
    resultReagents?: Reagent[];
    craftingStats?: string[];
}