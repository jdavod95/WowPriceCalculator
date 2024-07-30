export enum CraftingStat {
    INSPIRATION = "Inspiration",
    RESOURCEFULNESS = "Resourcefulness",
    MULTICRAFT = "Multicraft",
    CRAFTING_SPEED = "Crafting Speed"
}

export function getCraftingStatKey(value: string | undefined): CraftingStat | undefined {
    switch(value) {
        case CraftingStat.INSPIRATION.valueOf(): return CraftingStat.INSPIRATION;
        case CraftingStat.RESOURCEFULNESS.valueOf(): return CraftingStat.RESOURCEFULNESS;
        case CraftingStat.MULTICRAFT.valueOf(): return CraftingStat.MULTICRAFT;
        case CraftingStat.CRAFTING_SPEED.valueOf(): return CraftingStat.CRAFTING_SPEED;
        default: return undefined;
    }
}